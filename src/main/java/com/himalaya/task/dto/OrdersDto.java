package com.himalaya.task.dto;

import com.himalaya.task.common.enums.OrderStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record OrdersDto(

        Integer id,
        String trekDestination,
        LocalDate startDate,
        OrderStatus orderStatus,
        LocalDate endDate,
        GroupDto groups,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
