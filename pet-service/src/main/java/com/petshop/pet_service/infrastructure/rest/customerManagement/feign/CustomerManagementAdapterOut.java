package com.petshop.pet_service.infrastructure.rest.customerManagement.feign;

import com.petshop.pet_service.core.port.out.CustomerManagementPortOut;
import com.petshop.pet_service.infrastructure.rest.customerManagement.feign.mapper.CustomerMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerManagementAdapterOut implements CustomerManagementPortOut {

    private final CustomerManagementFeign feignClient;
    private final CustomerMapper mapper;

    @Override
    public int getAvailableSlots(LocalDateTime dateTime) {
        String formattedDate = mapper.toQueryParam(dateTime);

        try {
            var response = feignClient.getGeneralAvailability(formattedDate);

            if (response == null || response.getAvailableSlots() == null) {
                log.warn("API returned successfully, but no job posting data for the data: {}", formattedDate);
                return 0;
            }

            return response.getAvailableSlots();

        } catch (FeignException e) {
            log.error("Failed to query MS CustomerManagement. Status: {}. Error: {}", e.status(), e.getMessage());

            return 0;
        }
    }
}