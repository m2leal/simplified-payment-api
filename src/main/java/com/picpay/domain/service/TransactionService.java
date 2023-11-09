package com.picpay.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpay.domain.dto.TransactionDTO;
import com.picpay.domain.model.Transaction;
import com.picpay.domain.model.User;
import com.picpay.domain.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private NotificationService notificationService;
	
	public Transaction createTransaction(TransactionDTO transaction) throws Exception {
		User sender = this.userService.findUserById(transaction.senderId());
		User receiver = this.userService.findUserById(transaction.receiverId());
		
		userService.validateTransaction(sender, transaction.value());
		
		if(!this.authorizedTransaction(sender, transaction.value()))
			throw new Exception("Transação não autorizada");
		
		Transaction trn = new Transaction();
		trn.setAmount(transaction.value());
		trn.setSender(sender);
		trn.setReceiver(receiver);
		trn.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		receiver.setBalance(receiver.getBalance().add(transaction.value()));
		
		this.transactionRepository.save(trn);
		this.userService.saveUser(sender);
		this.userService.saveUser(receiver);
		
		this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
		this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");
		
		return trn;

	}
	
	public boolean authorizedTransaction(User sender, BigDecimal value) {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("http://localhost:8080/mock", Map.class);
		
		if(authorizationResponse.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
			String message = (String)authorizationResponse.getBody().get("message");

			return message.equals("Aprovado");
		}
		
		return false;
	}
}
