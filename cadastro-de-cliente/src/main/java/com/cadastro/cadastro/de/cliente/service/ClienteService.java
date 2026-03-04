package com.cadastro.cadastro.de.cliente.service;

import com.cadastro.cadastro.de.cliente.dto.ClientUpdateDto;
import com.cadastro.cadastro.de.cliente.dto.ClienteRequestDto;
import com.cadastro.cadastro.de.cliente.model.Client;
import com.cadastro.cadastro.de.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Client cadastrarCliente(ClienteRequestDto clientRequest) {
        var existingCustomer = clienteRepository.findByCpf(clientRequest.cpf());

        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("Error: A customer with this CPF already exists.");
        }

        var usuario = new Client(clientRequest);
        return clienteRepository.save(usuario);
    }

    public List<Client> listarClientes() {
        return clienteRepository.findAll();
    }

    public Client clientUpdate(Long id, ClientUpdateDto dto) {
        if (dto == null) {
            log.error("Tentativa de atualizar usuário com dados nulos.");
            throw new IllegalArgumentException("Dados de atualização não podem ser nulos.");
        }

        var cliente = buscarCliente(id);

        ClientUpdateDto dtoUpdate = new ClientUpdateDto(
                dto.nome(),
                dto.cpf(),
                dto.email(),
                dto.telefone()
        );

        cliente.upadate(dtoUpdate);
        log.info("Cliente com ID: {} atualizado.", id);
        return clienteRepository.save(cliente);
    }

    private Client buscarCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o id: " + id));
    }

    public Client deleterClient(Long id) {
        var cliente = buscarCliente(id);
        cliente.setAtivo(false);
        log.info("Cliente com ID: {} deletado.", id);
        return clienteRepository.save(cliente);
    }
}
