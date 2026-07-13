package com.himalaya.task.controller;

import com.himalaya.task.common.util.ResponseWrapper;
import com.himalaya.task.dto.DeviceDto;
import com.himalaya.task.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseWrapper<DeviceDto> create(@RequestBody DeviceDto deviceDto) {
        log.info("inside create device : controller");
        DeviceDto created = deviceService.create(deviceDto);
        return new ResponseWrapper<>(created, "created successfully", HttpStatus.OK.value(), true);
    }

    @GetMapping
    public ResponseWrapper<List<DeviceDto>> getAll(){
        log.info("inside get all device : controller");
        List<DeviceDto> getAllResponse = deviceService.getAll();
        return new ResponseWrapper<>(getAllResponse,"retrieved successfully",HttpStatus.OK.value(), true);
    }
}
