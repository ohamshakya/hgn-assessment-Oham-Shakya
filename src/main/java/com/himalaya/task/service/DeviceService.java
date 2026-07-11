package com.himalaya.task.service;

import com.himalaya.task.dto.DeviceDto;

import java.util.List;

public interface DeviceService {

    DeviceDto create(DeviceDto deviceDto);

    List<DeviceDto> getAll();
}
