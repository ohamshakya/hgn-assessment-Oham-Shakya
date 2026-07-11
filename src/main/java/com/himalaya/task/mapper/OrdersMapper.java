package com.himalaya.task.mapper;

import com.himalaya.task.common.enums.OrderStatus;
import com.himalaya.task.dto.GroupDto;
import com.himalaya.task.dto.MemberDto;
import com.himalaya.task.dto.OrdersDto;
import com.himalaya.task.entity.Groups;
import com.himalaya.task.entity.Member;
import com.himalaya.task.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrdersMapper {

    public static Orders toEntity(Groups groups,OrdersDto ordersDto){
        return Orders.builder()
                .trekDestination(ordersDto.trekDestination())
                .startDate(ordersDto.startDate())
                .endDate(ordersDto.endDate())
                .orderStatus(OrderStatus.NOT_ASSIGNED)
                .groups(groups)
                .build();
    }

    public static OrdersDto toDto(Orders orders){
        List<MemberDto> member = new ArrayList<>();
        for(Member i : orders.getGroups().getMember()){
            MemberDto dto = GroupMapper.toDto(i);
            member.add(dto);
        }
        GroupDto groups = GroupDto.builder()
                .groupName(orders.getGroups().getGroupName())
                .member(member)
                .build();
        return OrdersDto.builder()
                .id(orders.getId())
                .trekDestination(orders.getTrekDestination())
                .groups(groups)
                .startDate(orders.getStartDate())
                .endDate(orders.getEndDate())
                .orderStatus(orders.getOrderStatus())
                .createdAt(orders.getCreatedAt())
                .updatedAt(orders.getUpdatedAt())
                .build();

    }
}
