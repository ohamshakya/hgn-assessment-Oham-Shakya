package com.himalaya.task.service.impl;

import com.himalaya.task.common.enums.Availability;
import com.himalaya.task.common.enums.OrderStatus;
import com.himalaya.task.common.exception.ResourceNotFoundException;
import com.himalaya.task.dto.AssignDeviceDto;
import com.himalaya.task.dto.AssignDeviceResponseDto;
import com.himalaya.task.entity.AssignDevice;
import com.himalaya.task.entity.Device;
import com.himalaya.task.entity.Orders;
import com.himalaya.task.mapper.AssignDeviceMapper;
import com.himalaya.task.repo.AssignDeviceRepo;
import com.himalaya.task.repo.DeviceRepo;
import com.himalaya.task.repo.OrderRepo;
import com.himalaya.task.service.AssignDeviceService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
public class AssignDeviceServiceImpl implements AssignDeviceService {

    private final AssignDeviceRepo assignDeviceRepo;

    private final OrderRepo orderRepo;

    private final DeviceRepo deviceRepo;

    public AssignDeviceServiceImpl(AssignDeviceRepo assignDeviceRepo, OrderRepo orderRepo, DeviceRepo deviceRepo) {
        this.assignDeviceRepo = assignDeviceRepo;
        this.orderRepo = orderRepo;
        this.deviceRepo = deviceRepo;
    }

    @Override
    @Transactional
    public AssignDeviceResponseDto create(AssignDeviceDto assignDeviceDto) {
        log.info("inside assign device : service");
        Orders order = orderRepo.findById(assignDeviceDto.orderId()).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if(order.getOrderStatus().equals(OrderStatus.ASSIGNED)){
            throw new IllegalArgumentException("Sorry The order is already assigned");
        }
        order.setOrderStatus(OrderStatus.ASSIGNED);
        Device device = deviceRepo.findById(assignDeviceDto.deviceId()).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        if(device.getAvailability().equals(Availability.ASSIGNED)){
            throw new IllegalArgumentException("Device is already assigned");
        }
        device.setAvailability(Availability.ASSIGNED);
        orderRepo.save(order);
        deviceRepo.save(device);
        AssignDevice entity = AssignDeviceMapper.toEntity(order, device, assignDeviceDto);
        assignDeviceRepo.save(entity);
        return AssignDeviceMapper.toDto(entity);
    }

    @Override
    public AssignDeviceResponseDto getById(Integer id) {
        log.info("inside get assign device by id: service");
        AssignDevice assignDeviceNotFound = assignDeviceRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assign device not found"));
        return AssignDeviceMapper.toDto(assignDeviceNotFound);
    }

    @Override
    @Transactional
    public String returnDevice(Integer id) {
        AssignDevice assignedDeviceNotFound = assignDeviceRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assigned device not found"));
        Orders order = orderRepo.findById(assignedDeviceNotFound.getOrders().getId()).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Device device = deviceRepo.findById(assignedDeviceNotFound.getDevice().getId()).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        if(order.getOrderStatus().equals(OrderStatus.ASSIGNED) && device.getAvailability().equals(Availability.ASSIGNED)){
            order.setOrderStatus(OrderStatus.NOT_ASSIGNED);
            device.setAvailability(Availability.AVAILABLE);
            orderRepo.save(order);
            deviceRepo.save(device);
        }
        assignedDeviceNotFound.setReturnedAt(LocalDate.now());
        assignDeviceRepo.save(assignedDeviceNotFound);
        return "Returned successfully";
    }
}
