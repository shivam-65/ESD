package com.example.springweb.service;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.springweb.dto.CustomerRequest;
import com.example.springweb.dto.LoginRequest;
import com.example.springweb.entity.Customer;
import com.example.springweb.mapper.LoginMapper;
import com.example.springweb.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final CustomerRepo repo;
    private final LoginMapper mapper;

    public String loginUser(LoginRequest request) {
        Customer customer = mapper.toEntity(request);

        Optional<Customer> existingCustomer = repo.findByEmail(customer.getEmail());
        if (existingCustomer.isPresent()) {
            if (existingCustomer.get().getPassword().equals(customer.getPassword())) {
                return "Login successful";
            } else {
                return "Invalid password";
            }
        } else {
            return "User not found";
        }
    }
}