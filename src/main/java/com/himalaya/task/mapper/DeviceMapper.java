package com.himalaya.task.mapper;

import com.himalaya.task.common.enums.Availability;
import com.himalaya.task.dto.DeviceDto;
import com.himalaya.task.entity.Device;

public class DeviceMapper {
    public static Device toEntity(DeviceDto deviceDto) {
        return Device.builder()
                .deviceCode(deviceDto.deviceCode())
                .availability(Availability.AVAILABLE)
                .build();
    }

    public static DeviceDto toDto(Device device) {
        return DeviceDto.builder()
                .id(device.getId())
                .deviceCode(device.getDeviceCode())
                .availability(device.getAvailability())
                .build();
    }

}
