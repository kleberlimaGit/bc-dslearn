package com.bc.devsuperior.dslearn.services;

import java.time.Instant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bc.devsuperior.dslearn.dto.NotificationDTO;
import com.bc.devsuperior.dslearn.entities.Deliver;
import com.bc.devsuperior.dslearn.entities.Notification;
import com.bc.devsuperior.dslearn.entities.User;
import com.bc.devsuperior.dslearn.observers.DeliverRevisionObserver;
import com.bc.devsuperior.dslearn.repositories.NotificationRepository;

@Service
public class NotificationService implements DeliverRevisionObserver {

	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private DeliverService deliverService;
	
	@PostConstruct //executa este metodo logo apos o objeto ser instanciado
	private void initialize() {
		deliverService.subscribeDeliverRevisionObserver(this);
	}
	
	@Transactional(readOnly = true)
	public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadyOnly, Pageable pageable){
		User user = authService.authenticated();
		Page<Notification> page = repository.find(user, unreadyOnly, pageable);
		return page.map(x -> new NotificationDTO(x));
	}
	
	@Transactional
	public void saveDeliverNotification(Deliver deliver) {
		
		Long sectionId = deliver.getLesson().getSection().getId();
		Long resourceId = deliver.getLesson().getSection().getResource().getId();
		Long offerId = deliver.getLesson().getSection().getResource().getOffer().getId();
		
		String route = "/offers/"+ offerId + "/resources/" + resourceId + "/sections/" + sectionId;
		String text = deliver.getFeedback();
		Instant moment = Instant.now();
		User user = deliver.getEnrollment().getStudent();
		
		Notification notification = new Notification(null, text, moment, false, route, user);
		repository.save(notification);
	}

	@Override
	public void onSaveRevision(Deliver deliver) {
		saveDeliverNotification(deliver);
		
	}
}
