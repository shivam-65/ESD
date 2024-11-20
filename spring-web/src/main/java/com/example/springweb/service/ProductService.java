package com.example.springweb.service;

import com.example.springweb.dto.ProductsRequest;
import com.example.springweb.entity.Products;
import com.example.springweb.mapper.ProductMapper;
import com.example.springweb.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;
    private final ProductMapper mapper;

    public Products addProduct(ProductsRequest request) {
        System.out.println("==================== create service");
        Products product = mapper.toEntity(request);
        repo.save(product);
        return product;
    }


    public Products getProductById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Products getProductByName(String name) {
        return repo.findProductsByProductName(name);
    }

    public List<Products> getAllProducts() {
        return repo.findAll();
    }

    public Products updateProduct(Long id, Products updatedProduct) {
        Products existingProduct = getProductById(id);
        if (existingProduct != null) {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setPrice(updatedProduct.getPrice());
            return repo.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    public String getProductsWithPriceRange(String low, String high) {
        List<Products> products = repo.fetchTopProductByPrice(low,high);
        StringBuilder pro = new StringBuilder();
        for(Products product : products) {
            pro.append(product.getId() + " " + product.getProductName() + " " + product.getPrice()).append(",\n");
        }
        return pro.toString();
    }

}