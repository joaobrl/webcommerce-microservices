package com.petshop.customermanagement.dto;

public record CustomerRequestDto(
    String name,
    String cpf,
    String email,
    String phone
) {
}
