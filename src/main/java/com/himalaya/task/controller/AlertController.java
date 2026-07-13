package com.himalaya.task.controller;

import com.himalaya.task.common.util.ResponseWrapper;
import com.himalaya.task.dto.AlertDto;
import com.himalaya.task.service.AlertService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@Slf4j
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseWrapper<AlertDto> create(@Valid @RequestBody AlertDto alertDto){
        log.info("inside create alert : controller");
        AlertDto createAlert = alertService.create(alertDto);
        return new ResponseWrapper<>(createAlert, "created successfully", HttpStatus.OK.value(),true);
    }

    @GetMapping
    public ResponseWrapper<List<AlertDto>> getAll(){
        log.info("inside get all alert : controller");
        List<AlertDto> all = alertService.getAll();
        return new ResponseWrapper<>(all,"retrieved",HttpStatus.OK.value(),true);
    }

    @PatchMapping("/{id}/acknowledge")
    public ResponseWrapper<String> acknowledgeAlert(@PathVariable Integer id){
        log.info("inside ackKnowledge alert : controller");
        String response = alertService.alertAckKnowledge(id);
        return new ResponseWrapper<>(response,"acknowledged alert",HttpStatus.OK.value(),true);
    }
}
