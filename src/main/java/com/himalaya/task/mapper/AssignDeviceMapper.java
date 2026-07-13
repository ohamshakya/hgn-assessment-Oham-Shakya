package com.himalaya.task.mapper;

import com.himalaya.task.dto.*;
import com.himalaya.task.entity.AssignDevice;
import com.himalaya.task.entity.Device;
import com.himalaya.task.entity.Member;
import com.himalaya.task.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class AssignDeviceMapper {
    public static AssignDevice toEntity(Orders orders, Device device, AssignDeviceDto assignDeviceDto) {
        return AssignDevice.builder()
                .orders(orders)
                .device(device)
                .assignedAt(assignDeviceDto.assignedAt())
                .build();
    }

    public static AssignDeviceResponseDto toDto(AssignDevice assignDevice){
        List<MemberDto> memberDtoList = new ArrayList<>();
        for(Member members : assignDevice.getOrders().getGroups().getMember()){
            MemberDto membersDto = MemberDto.builder()
                    .id(members.getId())
                    .fullName(members.getFullName())
                    .email(members.getEmail())
                    .phoneNumber(members.getPhoneNumber())
                    .build();
            memberDtoList.add(membersDto);
        }

        GroupDto groups = GroupDto.builder()
                .id(assignDevice.getOrders().getGroups().getId())
                .groupName(assignDevice.getOrders().getGroups().getGroupName())
                .member(memberDtoList)
                .createdAt(assignDevice.getOrders().getGroups().getCreatedAt())
                .updatedAt(assignDevice.getOrders().getGroups().getUpdatedAt())
                .build();
        OrdersDto orders = OrdersDto.builder()
                .id(assignDevice.getOrders().getId())
                .trekDestination(assignDevice.getOrders().getTrekDestination())
                .startDate(assignDevice.getOrders().getStartDate())
                .endDate(assignDevice.getOrders().getEndDate())
                .groups(groups)
                .build();
        DeviceDto device = DeviceDto.builder()
                .id(assignDevice.getId())
                .deviceId(assignDevice.getDevice().getDeviceId())
                .availability(assignDevice.getDevice().getAvailability())
                .build();
        return AssignDeviceResponseDto.builder()
                .id(assignDevice.getId())
                .orders(orders)
                .device(device)
                .assignedAt(assignDevice.getAssignedAt())
                .build();
    }
}
