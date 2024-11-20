package com.example.springweb.service;

import java.util.Optional;
import com.example.springweb.dto.CustomerRequest;
import com.example.springweb.dto.CustomerResponse;
import com.example.springweb.entity.Customer;
import com.example.springweb.mapper.CustomerMapper;
import com.example.springweb.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.springweb.helper.EncryptionService;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;

    public Customer createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);

        customer.setPassword(encryptionService.encode(customer.getPassword()));

        repo.save(customer);
        return customer;
    }
}
