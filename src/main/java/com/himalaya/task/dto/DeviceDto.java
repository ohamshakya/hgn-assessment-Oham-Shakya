package com.himalaya.task.dto;

import com.himalaya.task.common.enums.Availability;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DeviceDto(

        Integer id,

        @NotBlank(message = "device code is required")
        String deviceCode,

        Availability availability
) {
}
