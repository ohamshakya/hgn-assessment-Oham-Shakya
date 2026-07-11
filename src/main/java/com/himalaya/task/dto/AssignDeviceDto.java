package com.himalaya.task.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AssignDeviceDto(

        Integer id,

        Integer orderId,

        Integer deviceId,

        LocalDate assignedAt,

        LocalDate returnedAt
) {
}
