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
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Customer Created";
    }
}
