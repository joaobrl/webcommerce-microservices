package com.petshop.customermanagement.infrastructure.persistence.postgresql.repository;

import com.petshop.customermanagement.core.domain.Customer;
import com.petshop.customermanagement.infrastructure.persistence.postgresql.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByCpf(String cpf);
}
