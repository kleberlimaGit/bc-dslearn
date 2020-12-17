package com.bc.devsuperior.dslearn.services;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bc.devsuperior.dslearn.dto.DeliverRevisionDTO;
import com.bc.devsuperior.dslearn.entities.Deliver;
import com.bc.devsuperior.dslearn.observers.DeliverRevisionObserver;
import com.bc.devsuperior.dslearn.repositories.DeliverRepository;

@Service
public class DeliverService {

	@Autowired
	private DeliverRepository repository;

	private Set<DeliverRevisionObserver> deliverRevisionObservers = new LinkedHashSet<>(); // linked mantem a ordem da coleçao
	
	@Transactional
	public void saveRevision(Long id, DeliverRevisionDTO dto ) {
		Deliver deliver = repository.getOne(id);
		deliver.setStatus(dto.getStatus());
		deliver.setFeedback(dto.getFeedback());
		deliver.setCorrectCount(dto.getCorrectCount());
		repository.save(deliver);
		for(DeliverRevisionObserver observer : deliverRevisionObservers) {
			observer.onSaveRevision(deliver);
		}
	
	}
	
	public void subscribeDeliverRevisionObserver(DeliverRevisionObserver observer) {
		deliverRevisionObservers.add(observer);
	}
	
}
