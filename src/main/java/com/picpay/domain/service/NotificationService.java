package com.picpay.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpay.domain.dto.NotificationDTO;
import com.picpay.domain.model.User;

@Service
public class NotificationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void sendNotification(User user, String message) throws Exception {
		NotificationDTO notificationDTO = new NotificationDTO(user.getEmail(), message);
		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/mock", notificationDTO, String.class);
		
		if(!responseEntity.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
			System.out.println("Erro ao enviar notificação");
			throw new Exception("Serviço de notificação está fora do ar");
		}
	}
}
