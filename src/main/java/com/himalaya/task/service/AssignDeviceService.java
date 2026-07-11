package com.himalaya.task.service;

import com.himalaya.task.dto.AssignDeviceDto;
import com.himalaya.task.dto.AssignDeviceResponseDto;

public interface AssignDeviceService {
    AssignDeviceResponseDto create(AssignDeviceDto assignDeviceDto);

    AssignDeviceResponseDto getById(Integer id);

    String returnDevice(Integer id);
}
