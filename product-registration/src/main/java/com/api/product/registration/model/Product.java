package com.api.product.registration.model;

import com.api.product.registration.config.tenant.TenantContext;
import com.api.product.registration.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = String.class)})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    @Embedded
    private Dimensions dimensions;
    private Boolean enabled;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    public Product(ProductRequestDto productRequest) {
        this.name = productRequest.getName();
        this.description = productRequest.getDescription();
        this.price = productRequest.getPrice();
        this.dimensions = new Dimensions(
                productRequest.getDimensions().getWeight(),
                productRequest.getDimensions().getHeight(),
                productRequest.getDimensions().getWidth(),
                productRequest.getDimensions().getLength()
        );
        this.enabled = true;
    }

    @PrePersist
    public void prePersist() {
        if (this.tenantId == null) {
            this.tenantId = TenantContext.getCurrentTenant();
        }
    }

    public void Update(ProductRequestDto productRequest) {
        if (productRequest.getName() != null) {
            this.name = productRequest.getName();
        }
        if (productRequest.getDescription() != null) {
            this.description = productRequest.getDescription();
        }
        if (productRequest.getPrice() != null) {
            this.price = productRequest.getPrice();
        }
        if (productRequest.getStock() != null) {
            this.stock = productRequest.getStock();
        }
        if (this.dimensions == null) {
            this.dimensions = new Dimensions();
        }
        if (productRequest.getDimensions().getWeight() != null) {
            this.dimensions.setWeight(productRequest.getDimensions().getWeight());
        }
        if (productRequest.getDimensions().getLength() != null) {
            this.dimensions.setLength(productRequest.getDimensions().getLength());
        }
        if (productRequest.getDimensions().getHeight() != null) {
            this.dimensions.setHeight(productRequest.getDimensions().getHeight());
        }
        if (productRequest.getDimensions().getWidth() != null) {
            this.dimensions.setWidth(productRequest.getDimensions().getWidth());
        }
    }

}
