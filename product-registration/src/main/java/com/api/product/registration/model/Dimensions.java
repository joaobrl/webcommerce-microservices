package com.api.product.registration.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dimensions {

    private Double weight;      // in kg (e.g., 1.5)
    private Double height;      // in cm
    private Double width;       // in cm
    private Double length;      // in cm
}
