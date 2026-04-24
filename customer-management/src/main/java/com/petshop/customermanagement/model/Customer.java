package com.petshop.customermanagement.model;

import com.petshop.customermanagement.config.tenant.TenantContext;
import com.petshop.customermanagement.dto.CustomerRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.List;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = String.class)})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String phone;
    private List<ServiceBooking> serviceBookings;
    private Boolean Enabled;
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    public Customer(CustomerRequestDto clientRequest) {
        this.name = clientRequest.name();
        this.cpf = clientRequest.cpf();
        this.email = clientRequest.email();
        this.phone = clientRequest.phone();
        this.Enabled = true;
    }
    @PrePersist
    public void prePersist() {
        if (this.tenantId == null) this.tenantId = TenantContext.getCurrentTenant();
    }

    public void upadate(CustomerRequestDto dto) {
        if (dto.name() != null) this.name = dto.name();
        if (dto.email() != null) this.email = dto.email();
        if (dto.phone() != null) this.phone = dto.phone();
    }

}
