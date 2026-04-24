package com.petshop.customermanagement.api.rest;

import com.petshop.customermanagement.core.port.in.dto.CustomerRequestDto;
import com.petshop.customermanagement.api.rest.dto.CustomerResponseDto;
import com.petshop.customermanagement.core.port.in.dto.CustomerUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface CustomerController {

    ResponseEntity registerCustomer(@Valid @RequestBody CustomerRequestDto clientRequest, UriComponentsBuilder uriBuilder);

    ResponseEntity<List<CustomerResponseDto>> listCustomers();

    ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerUpdateDto dto);

    ResponseEntity deleteCustomer(@PathVariable Long id);
}
