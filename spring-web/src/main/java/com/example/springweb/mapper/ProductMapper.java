package com.example.springweb.mapper;

import com.example.springweb.entity.Products;
import com.example.springweb.dto.ProductsRequest;
import com.example.springweb.dto.ProductsResponse;
import com.example.springweb.entity.Products;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Products toEntity(ProductsRequest request) {
        return Products.builder()
                .productName(request.productName())
                .price(request.price())
                .build();
    }

    public ProductsResponse toDto(Products product) {
        ProductsResponse response = new ProductsResponse(
                product.getId(), product.getProductName(), product.getPrice()
        );
        // Map other fields as needed
        return response;
    }
}
