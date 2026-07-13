package com.himalaya.task.dto;

import com.himalaya.task.common.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record OrdersDto(

        Integer id,
        @NotBlank(message = "trek destination is required")
        String trekDestination,
        LocalDate startDate,
        OrderStatus orderStatus,
        LocalDate endDate,
        GroupDto groups,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
