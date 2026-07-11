package com.himalaya.task.service;

import com.himalaya.task.dto.AlertDto;

import java.util.List;

public interface AlertService {

    AlertDto create(AlertDto alertDto);

    List<AlertDto> getAll();
}
