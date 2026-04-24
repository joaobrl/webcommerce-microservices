package com.petshop.customermanagement.infrastructure.persistence.postgresql.adapter;

import com.petshop.customermanagement.core.domain.Customer;
import com.petshop.customermanagement.core.port.out.CustomerPortOut;
import com.petshop.customermanagement.infrastructure.persistence.postgresql.entity.CustomerEntity;
import com.petshop.customermanagement.infrastructure.persistence.postgresql.mapper.CustomerMapper;
import com.petshop.customermanagement.infrastructure.persistence.postgresql.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerPersistenceAdapterOut implements CustomerPortOut {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf)
                .map(mapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        return mapper.toDomain(customerRepository.save(entity));
    }

    @Override
    public List<Customer> findAll() {
        return mapper.toDomainList(customerRepository.findAll());
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id)
                .map(mapper::toDomain);
    }
}
