package com.petshop.customermanagement.core.domain;

import com.petshop.customermanagement.core.port.in.dto.CustomerRequestDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Customer {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private Boolean enabled;
    private String tenantId;

    public Customer(CustomerRequestDto clientRequest) {
        this.name = clientRequest.name();
        this.cpf = clientRequest.cpf();
        this.email = clientRequest.email();
        this.phone = clientRequest.phone();
        this.enabled = true;
    }

    public void upadate(CustomerRequestDto dto) {
        if (dto.name() != null) this.name = dto.name();
        if (dto.email() != null) this.email = dto.email();
        if (dto.phone() != null) this.phone = dto.phone();
    }

}
