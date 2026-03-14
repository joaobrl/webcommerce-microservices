package com.petshop.pet_service.infrastructure.rest.customerManagement.feign;

import com.petshop.pet_service.infrastructure.rest.customerManagement.feign.dto.AvailabilityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-management", url = "${external.api.customer-management.url}")
public interface CustomerManagementFeign {

    @GetMapping("/api/v1/availability/general")
    AvailabilityDTO getGeneralAvailability(
            @RequestParam("dateTime") String dateTime
    );

}
