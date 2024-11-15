package com.example.springweb.mapper;

import com.example.springweb.dto.LoginRequest;
import com.example.springweb.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {
    public Customer toEntity(LoginRequest request) {
        return Customer.builder()
                .email(request.email())
                .password(request.password())
                .build();
    }
}