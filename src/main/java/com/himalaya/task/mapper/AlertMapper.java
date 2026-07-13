package com.himalaya.task.mapper;

import com.himalaya.task.common.enums.AlertStatus;
import com.himalaya.task.dto.AlertDto;
import com.himalaya.task.entity.Alert;
import com.himalaya.task.entity.Device;

public class AlertMapper {
    public static Alert toEntity(AlertDto alertDto,Device device){
        return Alert.builder()
                .device(device)
                .longitude(alertDto.longitude())
                .latitude(alertDto.latitude())
                .timestamp(alertDto.timestamp())
                .alertStatus(AlertStatus.PENDING)
                .build();
    }

    public static AlertDto toDto(Alert alert){
        return AlertDto.builder()
                .uuidCode(alert.getUuidCode())
                .deviceId(alert.getDevice().getDeviceId())
                .longitude(alert.getLongitude())
                .latitude(alert.getLatitude())
                .timestamp(alert.getTimestamp())
                .createdAt(alert.getCreatedAt())
                .updatedAt(alert.getUpdatedAt())
                .build();
    }
}
