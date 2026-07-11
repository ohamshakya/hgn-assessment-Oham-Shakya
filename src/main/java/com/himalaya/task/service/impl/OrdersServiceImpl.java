package com.himalaya.task.service.impl;

import com.himalaya.task.common.exception.ResourceNotFoundException;
import com.himalaya.task.dto.OrdersDto;
import com.himalaya.task.entity.Groups;
import com.himalaya.task.entity.Orders;
import com.himalaya.task.mapper.OrdersMapper;
import com.himalaya.task.repo.GroupRepo;
import com.himalaya.task.repo.OrderRepo;
import com.himalaya.task.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrdersServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    private final GroupRepo groupRepo;

    public OrdersServiceImpl(OrderRepo orderRepo, GroupRepo groupRepo) {
        this.orderRepo = orderRepo;
        this.groupRepo = groupRepo;
    }

    @Override
    public OrdersDto create(OrdersDto ordersDto) {
        log.info("inside create order : service");
        Groups groups = groupRepo.findById(ordersDto.groups().id()).orElseThrow(() -> new ResourceNotFoundException("Group not found"));
        Orders saveOrder = OrdersMapper.toEntity(groups, ordersDto);
        orderRepo.save(saveOrder);

        return OrdersMapper.toDto(saveOrder);
    }

    @Override
    public OrdersDto getById(Integer id) {
        log.info("inside get order by id : service");
        Orders orderNotFound = orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return OrdersMapper.toDto(orderNotFound);
    }
}
