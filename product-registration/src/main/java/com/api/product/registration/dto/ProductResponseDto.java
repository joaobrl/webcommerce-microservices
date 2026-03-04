package com.api.product.registration.dto;

import com.api.product.registration.model.Dimensions;
import com.api.product.registration.model.Product;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private DimensionsDto dimensions;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();

        if (product.getDimensions() != null) {
            this.dimensions = new DimensionsDto();
            this.dimensions.setWeight(product.getDimensions().getWeight());
            this.dimensions.setHeight(product.getDimensions().getHeight());
            this.dimensions.setWidth(product.getDimensions().getWidth());
            this.dimensions.setLength(product.getDimensions().getLength());
        }
    }
}
