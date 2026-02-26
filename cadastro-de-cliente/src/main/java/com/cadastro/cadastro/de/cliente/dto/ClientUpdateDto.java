package com.cadastro.cadastro.de.cliente.dto;

public record ClientUpdateDto(
        String nome,
        String cpf,
        String email,
        String telefone
) {
}
