package com.petshop.customermanagement.service;

import com.petshop.customermanagement.dto.CustomerRequestDto;
import com.petshop.customermanagement.model.Customer;
import com.petshop.customermanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer registreCustomer(CustomerRequestDto clientRequest) {
        var existingCustomer = customerRepository.findByCpf(clientRequest.cpf());

        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("Error: A customer with this CPF already exists.");
        }

        var customer = new Customer(clientRequest);
        return customerRepository.save(customer);
    }

    public List<Customer> customerList() {
        return customerRepository.findAll();
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
        return customerRepository.save(customer);
    }

    private Customer findCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
    }

    public Customer deleteCustomer(Long id) {
        var customer = findCustomer(id);
        customer.setEnabled(false);
        log.info("Customer with ID {} deleted.", id);
        return customerRepository.save(customer);
    }
}
