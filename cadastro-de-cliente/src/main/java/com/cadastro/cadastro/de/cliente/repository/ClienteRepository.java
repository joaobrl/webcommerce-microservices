package com.cadastro.cadastro.de.cliente.repository;

import com.cadastro.cadastro.de.cliente.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}
