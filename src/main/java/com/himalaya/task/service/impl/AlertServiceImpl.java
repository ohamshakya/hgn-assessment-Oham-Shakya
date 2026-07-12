package com.himalaya.task.service.impl;

import com.himalaya.task.common.enums.AlertStatus;
import com.himalaya.task.common.exception.ResourceNotFoundException;
import com.himalaya.task.dto.AlertDto;
import com.himalaya.task.entity.Alert;
import com.himalaya.task.mapper.AlertMapper;
import com.himalaya.task.repo.AlertRepo;
import com.himalaya.task.service.AlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AlertServiceImpl implements AlertService {

    private final AlertRepo alertRepo;

    public AlertServiceImpl(AlertRepo alertRepo) {
        this.alertRepo = alertRepo;
    }

    @Override
    public AlertDto create(AlertDto alertDto) {
        log.info("inside create alert : service");
        Alert alert = AlertMapper.toEntity(alertDto);

        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);

        Optional<Alert> existing =
                alertRepo.checkDeviceIdAndTimestamp(alertDto.deviceId(), fiveMinutesAgo);

        if (existing.isPresent()) {
            throw new IllegalArgumentException("Duplicate alert");
        }

        alertRepo.save(alert);
        return AlertMapper.toDto(alert);
    }

    @Override
    public List<AlertDto> getAll() {
        log.info("inside get all alert : service");
        return alertRepo.findAll().stream().map(AlertMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public String alertAckKnowledge(Integer id) {
        log.info("inside alert ack knowledge ; service");
        Alert alert = alertRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
        alert.setAlertStatus(AlertStatus.ACK_KNOWLEDGE);
        alertRepo.save(alert);
        return "Ack knowledge";
    }
}
