package com.petshop.customermanagement.core.port.in;

import com.petshop.customermanagement.core.domain.Customer;
import com.petshop.customermanagement.core.port.in.dto.CustomerRequestDto;
import com.petshop.customermanagement.core.port.in.dto.CustomerUpdateDto;

import java.util.List;

public interface CustomerPortIn {

    Customer registerCustomer (CustomerRequestDto clientRequest);

    List<Customer> customerList();

    Customer updateCustomer(Long id, CustomerUpdateDto dto);

    Customer findCustomer(Long id);

    Customer deleteCustomer(Long id);
}
