package com.petshop.pet_service.core.domain;

import com.petshop.pet_service.core.domain.enums.ServiceType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ServiceDetails {

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type")
    private ServiceType serviceType;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    private Integer durationInMinutes;

    public ServiceDetails(ServiceType serviceType) {
        this.serviceType = serviceType;
        switch (serviceType) {
            case TOSAGEM -> {
                this.price = new BigDecimal("50.00");
                this.durationInMinutes = 30;
            }
            case BANHO -> {
                this.price = new BigDecimal("80.00");
                this.durationInMinutes = 45;
            }
            case TOSA_E_BANHO -> {
                this.price = new BigDecimal("120.00");
                this.durationInMinutes = 60;
            }
        }

    }
}