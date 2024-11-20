package com.example.springweb.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductsResponse(
        @JsonProperty("id")
        Long id,

        @JsonProperty("product_name")
        String productName,

        @JsonProperty("price")
        Float price) {
}