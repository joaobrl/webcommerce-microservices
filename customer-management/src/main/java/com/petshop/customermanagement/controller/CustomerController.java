package com.petshop.customermanagement.controller;

import com.petshop.customermanagement.dto.CustomerRequestDto;
import com.petshop.customermanagement.dto.CustomerResponseDto;
import com.petshop.customermanagement.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/customers")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Transactional
    public ResponseEntity registerCustomer(@Valid @RequestBody CustomerRequestDto clientRequest, UriComponentsBuilder uriBuilder) {
        var customer = customerService.registreCustomer(clientRequest);
        var uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerResponseDto(customer));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerResponseDto>> listCustomers() {
        var customer = customerService.customerList()
                .stream()
                .map(CustomerResponseDto::new)
                .toList();
        return ResponseEntity.ok(customer);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerRequestDto dto) {
        var customer = customerService.updateCustomer(id, dto);
        return ResponseEntity.ok(new CustomerResponseDto(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        var customer = customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
