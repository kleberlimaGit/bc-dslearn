package com.bc.devsuperior.dslearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bc.devsuperior.dslearn.entities.User;
import com.bc.devsuperior.dslearn.repositories.UserRepository;
import com.bc.devsuperior.dslearn.services.exception.ForbiddenException;
import com.bc.devsuperior.dslearn.services.exception.UnauthorizedException;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public User authenticated() {
		// este metodo pega o usuario que ja foi previamente reconhecido pelo spring security
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(username);
		}
		catch (Exception e) {
			throw new UnauthorizedException("Invalid User");
		}
	}
	
	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		if(!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN") ) {
			throw new ForbiddenException("Access denied");
		}
	}
}
