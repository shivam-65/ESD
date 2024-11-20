package com.example.springweb.controller;

import com.example.springweb.dto.ProductsResponse;
import com.example.springweb.dto.ProductsRequest;
import com.example.springweb.entity.Products;
import com.example.springweb.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService  productService;

    @PostMapping // for post request
    public ResponseEntity<Products> addProduct(@RequestBody @Valid ProductsRequest request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @GetMapping("/{id}") // for get request to fetch a product by its ID
    public ResponseEntity<Products> getProductById(@PathVariable("id") Long id) {
        ResponseEntity<Products> ok = ResponseEntity.ok(productService.getProductById(id));
        return ok;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Products> getProductByName(@PathVariable String name) {
        Products product = productService.getProductByName(name);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // Read All Products
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products updatedProduct) {
        System.out.println(id);
        Products product = productService.updateProduct(id, updatedProduct);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{low}/{high}")
    public ResponseEntity<String> getProducts(@PathVariable String low, @PathVariable String high) {
        return ResponseEntity.ok(productService.getProductsWithPriceRange(low,high));
    }

}
