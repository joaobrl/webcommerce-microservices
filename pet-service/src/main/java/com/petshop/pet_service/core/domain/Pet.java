package com.petshop.pet_service.core.domain;

import com.petshop.pet_service.core.domain.enums.PetType;
import com.petshop.pet_service.core.domain.enums.SizeCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Pet {

    private String petName;
    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type")
    private PetType petType;
    private String petBreed;
    private SizeCategory petSize;
    private Double weightInKg;
    private String petHealthIssues;
    private String ownerName;
    private String ownerCpf;
    private String ownerContact;
}
