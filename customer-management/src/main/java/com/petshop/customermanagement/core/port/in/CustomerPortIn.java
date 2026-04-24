package com.petshop.customermanagement.core.port.in;

import com.petshop.customermanagement.core.domain.Customer;
import com.petshop.customermanagement.core.port.in.dto.CustomerRequestDto;

import java.util.List;

public interface CustomerPortIn {

    Customer registreCustomer(CustomerRequestDto clientRequest);

    List<Customer> customerList();

    Customer updateCustomer(Long id, CustomerRequestDto dto);

    Customer findCustomer(Long id);

    Customer deleteCustomer(Long id);
}
