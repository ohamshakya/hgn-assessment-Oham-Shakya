package com.himalaya.task.controller;

import com.himalaya.task.common.util.ResponseWrapper;
import com.himalaya.task.dto.AssignDeviceDto;
import com.himalaya.task.dto.AssignDeviceResponseDto;
import com.himalaya.task.mapper.AssignDeviceMapper;
import com.himalaya.task.repo.AssignDeviceRepo;
import com.himalaya.task.service.AssignDeviceService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assign-device")
@Slf4j
public class AssignDeviceController {

    private final AssignDeviceService assignDeviceService;

    public AssignDeviceController(AssignDeviceService assignDeviceService) {
        this.assignDeviceService = assignDeviceService;
    }

    @PostMapping
    public ResponseWrapper<AssignDeviceResponseDto> create(@RequestBody AssignDeviceDto assignDeviceDto){
        log.info("inside assign device : controller");
        AssignDeviceResponseDto assignDeviceResponseDto = assignDeviceService.create(assignDeviceDto);
        return new ResponseWrapper<>(assignDeviceResponseDto,"assigned", HttpStatus.OK.value(), true);
    }

    @GetMapping("/{id}")
    public ResponseWrapper<AssignDeviceResponseDto> getById(@PathVariable Integer id){
        log.info("inside get assign device by id : controller");
        AssignDeviceResponseDto byId = assignDeviceService.getById(id);
        return new ResponseWrapper<>(byId,"retrieved",HttpStatus.OK.value(),true);
    }

    @PatchMapping("/{id}")
    public ResponseWrapper<String> returnDevice(@PathVariable Integer id){
        log.info("inside return assign device : controller");
        String s = assignDeviceService.returnDevice(id);
        return new ResponseWrapper<>(s,"returned successfully",HttpStatus.OK.value(),true);
    }


}
