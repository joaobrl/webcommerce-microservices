package com.cadastro.cadastro.de.cliente.model;

import com.cadastro.cadastro.de.cliente.config.tenant.TenantContext;
import com.cadastro.cadastro.de.cliente.dto.ClientUpdateDto;
import com.cadastro.cadastro.de.cliente.dto.ClienteRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = String.class)})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private String email;
    private String telefone;
    private Boolean ativo;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    public Client(ClienteRequestDto clientRequest) {
        this.nome = clientRequest.nome();
        this.cpf = clientRequest.cpf();
        this.email = clientRequest.email();
        this.telefone = clientRequest.telefone();
        this.ativo = true;
    }
    @PrePersist
    public void prePersist() {
        if (this.tenantId == null) {
            this.tenantId = TenantContext.getCurrentTenant();
        }
    }

    public void upadate(ClientUpdateDto dto) {
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.telefone() != null) {
            this.telefone = dto.telefone();
        }
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
    }

}
