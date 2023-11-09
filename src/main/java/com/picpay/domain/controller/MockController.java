package com.picpay.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.domain.dto.NotificationDTO;

import lombok.Getter;

@RestController
@RequestMapping(value = "/mock")
public class MockController {
	
	@GetMapping
	public ResponseEntity<Message> getMessage() {
		return ResponseEntity.ok(new Message());
	}
	
	@PostMapping
	public ResponseEntity<?> sendEmail(@RequestBody NotificationDTO notification) {
		System.out.println("Email: " + notification.email());
		System.out.println("Message: " + notification.message());
		return ResponseEntity.ok().build();
	}
	
	@Getter
	private class Message {
		private String message;
		
		private Message() {
			message = (Math.random()>=0.3) ? "Aprovado" : "Reprovado";
		}
	}
	
}
