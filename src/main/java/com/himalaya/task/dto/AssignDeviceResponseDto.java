package com.himalaya.task.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AssignDeviceResponseDto(
        Integer id,

        OrdersDto orders,

        DeviceDto device,

        LocalDate assignedAt,

        LocalDate returnedAt
) {
}
