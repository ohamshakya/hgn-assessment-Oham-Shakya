package com.himalaya.task.controller;

import com.himalaya.task.common.util.ResponseWrapper;
import com.himalaya.task.dto.OrdersDto;
import com.himalaya.task.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseWrapper<OrdersDto> create(@RequestBody OrdersDto ordersDto){
        log.info("inside create order : controller");
        OrdersDto response = orderService.create(ordersDto);
        return new ResponseWrapper<>(response,"created successfully", HttpStatus.OK.value(),true);
    }

    @GetMapping("/{id}")
    public ResponseWrapper<OrdersDto> getById(@PathVariable Integer id){
        log.info("inside get order by id : controller");
        OrdersDto byId = orderService.getById(id);
        return new ResponseWrapper<>(byId,"retrieved successfully",HttpStatus.OK.value(),true);
    }
}
