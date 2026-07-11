package com.himalaya.task.service;

import com.himalaya.task.dto.OrdersDto;

public interface OrderService {

    OrdersDto create(OrdersDto ordersDto);

    OrdersDto getById(Integer id);
}
