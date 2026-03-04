package com.api.product.registration.dto;

import com.api.product.registration.model.Dimensions;
import jakarta.persistence.Embedded;
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
    private DimensionsDto dimensions;

    public ProductRequestDto(String name, String description, Double price, Integer stock, DimensionsDto dimensions) {
    }
}
