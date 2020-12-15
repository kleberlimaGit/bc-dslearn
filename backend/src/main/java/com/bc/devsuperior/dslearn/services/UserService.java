package com.bc.devsuperior.dslearn.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bc.devsuperior.dslearn.entities.User;
import com.bc.devsuperior.dslearn.repositories.UserRepository;


@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		if(user == null) {
			logger.error("Usuário não encontrado: " + username);
			throw new UsernameNotFoundException("Email não encontrado");
		}
		logger.info("Usuário encontrado: " + username);
		
		return user;
	}

}
