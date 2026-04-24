package com.petshop.customermanagement.core.application.service;

import com.petshop.commons.exception.ConflictException;
import com.petshop.commons.exception.NotFoundException;
import com.petshop.customermanagement.core.domain.Customer;
import com.petshop.customermanagement.core.port.in.CustomerPortIn;
import com.petshop.customermanagement.core.port.in.dto.CustomerRequestDto;
import com.petshop.customermanagement.core.port.in.dto.CustomerUpdateDto;
import com.petshop.customermanagement.core.port.out.CustomerPortOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements CustomerPortIn {

    private final CustomerPortOut customerPortOut;

    @Override
    public Customer registerCustomer (CustomerRequestDto request) {
        customerPortOut.findByCpf(request.getCpf())
                .ifPresent(c -> {
                    throw new ConflictException("CPF already exists: " + request.getCpf());
                });

        var customer = new Customer(
                request.getName(),
                request.getCpf(),
                request.getEmail(),
                request.getPhone()
        );
        log.info("Customer registered with CPF: {}", request.getCpf());
        return customerPortOut.save(customer);
    }

    @Override
    public List<Customer> customerList() {
        return customerPortOut.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, CustomerUpdateDto dto) {
        var customer = findCustomer(id);
        customer.update(dto.getName(), dto.getEmail(), dto.getPhone());
        log.info("Customer with ID: {} updated.", id);
        return customerPortOut.save(customer);
    }

    @Override
    public Customer findCustomer(Long id) {
        return customerPortOut.findById(id)
                .orElseThrow(() ->  new NotFoundException("Customer", id));
    }

    @Override
    public Customer deleteCustomer(Long id) {
        var customer = findCustomer(id);
        customer.setEnabled(false);
        log.info("Customer with ID {} deleted.", id);
        return customerPortOut.save(customer);
    }
}
