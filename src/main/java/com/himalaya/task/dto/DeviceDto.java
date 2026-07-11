package com.himalaya.task.dto;

import com.himalaya.task.common.enums.Availability;
import lombok.Builder;

@Builder
public record DeviceDto(

        Integer id,

        String deviceCode,

        Availability availability
) {
}
