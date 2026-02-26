package com.cadastro.cadastro.de.cliente.dto;

import com.cadastro.cadastro.de.cliente.model.Client;

public record ClienteResponseDto(
    Long id,
    String nome,
    String cpf,
    String email,
    String telefone
) {
    public ClienteResponseDto(Client cliente) {
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getEmail(),
            cliente.getTelefone()
        );
    }
}
