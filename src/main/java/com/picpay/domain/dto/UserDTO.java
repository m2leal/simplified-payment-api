package com.picpay.domain.dto;

import java.math.BigDecimal;

import com.picpay.domain.model.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
	
}
