package com.himalaya.task.mapper;

import com.himalaya.task.dto.*;
import com.himalaya.task.entity.AssignDevice;
import com.himalaya.task.entity.Device;
import com.himalaya.task.entity.Orders;

public class AssignDeviceMapper {
    public static AssignDevice toEntity(Orders orders, Device device, AssignDeviceDto assignDeviceDto) {
        return AssignDevice.builder()
                .orders(orders)
                .device(device)
                .assignedAt(assignDeviceDto.assignedAt())
                .build();
    }

    public static AssignDeviceResponseDto toDto(AssignDevice assignDevice){
        GroupDto groups = GroupDto.builder()
                .id(assignDevice.getOrders().getGroups().getId())
                .groupName(assignDevice.getOrders().getGroups().getGroupName())
                .build();
        OrdersDto orders = OrdersDto.builder()
                .trekDestination(assignDevice.getOrders().getTrekDestination())
                .startDate(assignDevice.getOrders().getStartDate())
                .endDate(assignDevice.getOrders().getEndDate())
                .groups(groups)
                .build();
        DeviceDto device = DeviceDto.builder()
                .id(assignDevice.getId())
                .deviceCode(assignDevice.getDevice().getDeviceCode())
                .availability(assignDevice.getDevice().getAvailability())
                .build();
        return AssignDeviceResponseDto.builder()
                .orders(orders)
                .device(device)
                .assignedAt(assignDevice.getAssignedAt())
                .build();
    }
}
