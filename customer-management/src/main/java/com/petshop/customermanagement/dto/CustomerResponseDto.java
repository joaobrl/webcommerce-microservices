package com.petshop.customermanagement.dto;

import com.petshop.customermanagement.model.Customer;

public record CustomerResponseDto(
    Long id,
    String name,
    String cpf,
    String email,
    String phone
) {
    public CustomerResponseDto(Customer cliente) {
        this(
            cliente.getId(),
            cliente.getName(),
            cliente.getCpf(),
            cliente.getEmail(),
            cliente.getPhone()
        );
    }
}
