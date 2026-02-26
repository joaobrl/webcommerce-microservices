package com.cadastro.cadastro.de.cliente.dto;

public record ClienteRequestDto(
    String nome,
    String cpf,
    String email,
    String telefone
) {
}
