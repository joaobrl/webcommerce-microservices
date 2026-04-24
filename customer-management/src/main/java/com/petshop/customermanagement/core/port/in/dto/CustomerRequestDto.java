package com.petshop.customermanagement.core.port.in.dto;

public record CustomerRequestDto(
    String name,
    String cpf,
    String email,
    String phone
) {
}
