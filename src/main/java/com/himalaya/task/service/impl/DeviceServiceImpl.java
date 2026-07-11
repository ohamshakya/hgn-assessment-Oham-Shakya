package com.himalaya.task.service.impl;

import com.himalaya.task.dto.DeviceDto;
import com.himalaya.task.entity.Device;
import com.himalaya.task.mapper.DeviceMapper;
import com.himalaya.task.repo.DeviceRepo;
import com.himalaya.task.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepo deviceRepo;

    public DeviceServiceImpl(DeviceRepo deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    @Override
    public DeviceDto create(DeviceDto deviceDto) {
        log.info("inside create device : service");
        Device deviceSave = DeviceMapper.toEntity(deviceDto);
        deviceRepo.save(deviceSave);
        return DeviceMapper.toDto(deviceSave);
    }

    @Override
    public List<DeviceDto> getAll() {
        log.info("inside get all device : service");
        return deviceRepo.findAll().stream().map(DeviceMapper::toDto).collect(Collectors.toList());
    }
}
