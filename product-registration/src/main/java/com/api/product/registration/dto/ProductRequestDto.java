package com.api.product.registration.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private Double price;
    private Integer stock;

    public ProductRequestDto(String name, String description, Double price, Integer stock) {
    }
}
