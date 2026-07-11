package com.himalaya.task.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AlertDto(
        String uuidCode,

        String deviceId,

        String latitude,

        String longitude,

        LocalDateTime timestamp,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
