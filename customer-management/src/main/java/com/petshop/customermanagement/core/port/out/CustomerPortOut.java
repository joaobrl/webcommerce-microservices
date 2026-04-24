package com.petshop.customermanagement.core.port.out;

import com.petshop.customermanagement.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerPortOut {

    Optional<Customer> findByCpf(String cpf);

    Customer save(Customer customer);

    List<Customer> findAll();

    Customer findById(Long id);
}
