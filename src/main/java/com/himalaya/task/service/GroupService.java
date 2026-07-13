package com.himalaya.task.service;

import com.himalaya.task.dto.GroupDto;

import java.util.List;

public interface GroupService {

    GroupDto create(GroupDto groupDto);

    List<GroupDto> getAll();
}
