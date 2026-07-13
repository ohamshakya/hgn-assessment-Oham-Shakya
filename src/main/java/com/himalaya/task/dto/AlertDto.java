package com.himalaya.task.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AlertDto(
        String uuidCode,

        @NotBlank(message = "Device id should not be blank")
        String deviceId,

        String latitude,

        String longitude,

        LocalDateTime timestamp,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
