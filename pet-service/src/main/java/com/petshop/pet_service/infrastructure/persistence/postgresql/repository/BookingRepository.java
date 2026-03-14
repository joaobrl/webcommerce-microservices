package com.petshop.pet_service.infrastructure.persistence.postgresql.repository;

import com.petshop.pet_service.core.domain.PetBooking;
import com.petshop.pet_service.core.port.in.dto.BookingSearchCriteriaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<PetBooking, UUID> {

    @Query("""
        SELECT b FROM PetBooking b 
        WHERE (:#{#criteria.petName} IS NULL OR LOWER(b.pet.petName) LIKE LOWER(CONCAT('%', :#{#criteria.petName}, '%')))
        AND (:#{#criteria.ownerCpf} IS NULL OR b.pet.ownerCpf = :#{#criteria.ownerCpf})
        AND (:#{#criteria.serviceType} IS NULL OR b.serviceDetails.serviceType = :#{#criteria.serviceType})
        AND (:#{#criteria.status} IS NULL OR b.status = :#{#criteria.status})
        AND (CAST(:#{#criteria.date} AS string) IS NULL OR CAST(b.bookingDateTime AS date) = :#{#criteria.date})
    """)
    List<PetBooking> findByCriteria(BookingSearchCriteriaDto criteria);
}
