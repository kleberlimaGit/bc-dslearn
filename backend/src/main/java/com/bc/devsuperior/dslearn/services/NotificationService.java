package com.bc.devsuperior.dslearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bc.devsuperior.dslearn.dto.NotificationDTO;
import com.bc.devsuperior.dslearn.entities.Notification;
import com.bc.devsuperior.dslearn.entities.User;
import com.bc.devsuperior.dslearn.repositories.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository repositoy;
	
	@Autowired
	private AuthService authService;
	
	public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadyOnly, Pageable pageable){
		User user = authService.authenticated();
		Page<Notification> page = repositoy.find(user, unreadyOnly, pageable);
		return page.map(x -> new NotificationDTO(x));
	}
}
