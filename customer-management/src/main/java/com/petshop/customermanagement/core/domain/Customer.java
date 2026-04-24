package com.petshop.customermanagement.core.domain;

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

    public Customer(String name, String cpf, String email, String phone) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.enabled = true;
    }

    public void update(String name, String email, String phone) {
        if (name != null) this.name = name;
        if (email != null) this.email = email;
        if (phone != null) this.phone = phone;
    }

}
