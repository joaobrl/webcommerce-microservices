package com.petshop.customermanagement.api.rest;

import com.petshop.customermanagement.core.port.in.CustomerPortIn;
import com.petshop.customermanagement.core.port.in.dto.CustomerRequestDto;
import com.petshop.customermanagement.core.port.out.dto.CustomerResponseDto;
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
public class CustomerControllerImpl implements CustomerController{

    private final CustomerPortIn portIn;

    @PostMapping("/api/v1/register")
    @Transactional
    public ResponseEntity registerCustomer(@Valid @RequestBody CustomerRequestDto clientRequest, UriComponentsBuilder uriBuilder) {
        var customer = portIn.registreCustomer(clientRequest);
        var uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerResponseDto(customer));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerResponseDto>> listCustomers() {
        var customer = portIn.customerList()
                .stream()
                .map(CustomerResponseDto::new)
                .toList();
        return ResponseEntity.ok(customer);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerRequestDto dto) {
        var customer = portIn.updateCustomer(id, dto);
        return ResponseEntity.ok(new CustomerResponseDto(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        var customer = portIn.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
