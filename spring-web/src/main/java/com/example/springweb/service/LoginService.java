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
import com.example.springweb.helper.EncryptionService;
import com.example.springweb.helper.JWTHelper;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final CustomerRepo repo;
    private final LoginMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String loginUser(LoginRequest request) {
        Customer customer = mapper.toEntity(request);

        Optional<Customer> existingCustomer = repo.findByEmail(customer.getEmail());
        if (existingCustomer.isPresent()) {
            if(!encryptionService.validates(request.password(), existingCustomer.get().getPassword())) {
                return "Wrong Password or Email";
            }
            else {
                return jwtHelper.generateToken(request.email());
            }
        } else {
            return "User not found";
        }
    }
}