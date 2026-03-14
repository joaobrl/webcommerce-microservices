package com.petshop.pet_service.core.port.out.dto;

import com.petshop.pet_service.core.domain.enums.ServiceType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceDetailsResponseDto {

    private ServiceType serviceType;

    // Preço do serviço, pode ser calculado com base no tipo de serviço e no tamanho do pet
    private BigDecimal price; // campo usado apenas para exibir o preço calculado, não é enviado pelo cliente

    // Duração estimada do serviço, pode ser calculada com base no tipo de serviço e no tamanho do pet
    private Integer durationInMinutes;  // duração estimada do serviço em minutos, pode ser usada para agendamento e cálculo de tempo
}
