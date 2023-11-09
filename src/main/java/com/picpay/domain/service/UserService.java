package com.picpay.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.domain.dto.UserDTO;
import com.picpay.domain.model.User;
import com.picpay.domain.model.UserType;
import com.picpay.domain.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception {
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Usuário do tipo LOJISTA não está autorizado a realizar transações.");
		}
		
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Usuário insuficiente.");
		}
	}
	
	public User findUserById(Long id) throws Exception {
		return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
	}
	
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}

	public User createUser(UserDTO data) {
		User user = new User(data);
		return this.saveUser(user);
	}
	
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
}
