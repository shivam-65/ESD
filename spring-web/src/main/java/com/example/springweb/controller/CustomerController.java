package com.example.springweb.controller;

import com.example.springweb.dto.CustomerRequest;
import com.example.springweb.entity.Customer;
import com.example.springweb.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRequest request) {
        Customer c = customerService.createCustomer(request);
        System.out.print("Customer Data : "+c.toString());
        return ResponseEntity.ok(c);
    }
}
