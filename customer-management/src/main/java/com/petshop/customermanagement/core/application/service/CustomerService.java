package com.petshop.customermanagement.core.application.service;

import com.petshop.customermanagement.core.port.in.CustomerPortIn;
import com.petshop.customermanagement.core.port.in.dto.CustomerRequestDto;
import com.petshop.customermanagement.core.domain.Customer;
import com.petshop.customermanagement.core.port.out.CustomerPortOut;
import com.petshop.customermanagement.infrastructure.persistence.postgresql.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements CustomerPortIn {

    private final CustomerPortOut customerPortOut;

    public Customer registreCustomer(CustomerRequestDto clientRequest) {
        var existingCustomer = customerPortOut.findByCpf(clientRequest.cpf());

        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("Error: A customer with this CPF already exists.");
        }

        var customer = new Customer(clientRequest);
        return customerPortOut.save(customer);
    }

    public List<Customer> customerList() {
        return customerPortOut.findAll();
    }

    public Customer updateCustomer(Long id, CustomerRequestDto dto) {
        if (dto == null) {
            log.error("Attempt update customer with data null");
            throw new IllegalArgumentException("Update data cannot be null");
        }

        var customer = findCustomer(id);

        CustomerRequestDto dtoUpdate = new CustomerRequestDto(
                dto.name(),
                dto.cpf(),
                dto.email(),
                dto.phone()
        );

        customer.upadate(dtoUpdate);
        log.info("Customer with ID: {} updated.", id);
        return customerPortOut.save(customer);
    }

    public Customer findCustomer(Long id) {
        return customerPortOut.findById(id);
    }

    public Customer deleteCustomer(Long id) {
        var customer = findCustomer(id);
        customer.setEnabled(false);
        log.info("Customer with ID {} deleted.", id);
        return customerPortOut.save(customer);
    }
}
