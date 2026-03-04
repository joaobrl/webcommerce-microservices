package com.api.product.registration.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DimensionsDto {
    @NotNull
    private Double weight;
    @NotNull
    private Double height;
    @NotNull
    private Double width;
    @NotNull
    private Double length;
}
