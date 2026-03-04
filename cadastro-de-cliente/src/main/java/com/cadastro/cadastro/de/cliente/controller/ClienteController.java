package com.cadastro.cadastro.de.cliente.controller;

import com.cadastro.cadastro.de.cliente.dto.ClientUpdateDto;
import com.cadastro.cadastro.de.cliente.dto.ClienteRequestDto;
import com.cadastro.cadastro.de.cliente.dto.ClienteResponseDto;
import com.cadastro.cadastro.de.cliente.service.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/clientes")
@RestController
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @Transactional
    public ResponseEntity registerCustomer(@Valid @RequestBody ClienteRequestDto clientRequest, UriComponentsBuilder uriBuilder) {
        var cliente = clienteService.cadastrarCliente(clientRequest);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteResponseDto(cliente));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteResponseDto>> listCustomers() {
        var client = clienteService.listarClientes()
                .stream()
                .map(ClienteResponseDto::new)
                .toList();
        return ResponseEntity.ok(client);
    }

    @PatchMapping("/{id}/atualizar")
    public ResponseEntity atualizarUsuario(@PathVariable Long id, @RequestBody @Valid ClientUpdateDto dto) {
        var cliente = clienteService.clientUpdate(id, dto);
        return ResponseEntity.ok(new ClienteResponseDto(cliente));
    }

    @DeleteMapping("/{id}/deletar")
    public ResponseEntity deletarCliente(@PathVariable Long id) {
        var cliente = clienteService.deleterClient(id);
        return ResponseEntity.noContent().build();
    }
}
