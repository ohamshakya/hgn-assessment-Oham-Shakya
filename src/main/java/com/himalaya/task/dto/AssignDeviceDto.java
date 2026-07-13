package com.himalaya.task.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AssignDeviceDto(

        Integer id,

        @NotBlank(message = "order should be not blank")
        Integer orderId,

        @NotBlank(message = "device should not be blank")
        Integer deviceId,

        @NotBlank(message = "assignedAt should not be blank")
        LocalDate assignedAt,

        LocalDate returnedAt
) {
}
