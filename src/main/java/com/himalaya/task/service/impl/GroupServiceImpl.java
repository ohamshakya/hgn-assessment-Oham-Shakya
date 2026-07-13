package com.himalaya.task.service.impl;

import com.himalaya.task.common.exception.AlreadyExistsException;
import com.himalaya.task.dto.GroupDto;
import com.himalaya.task.entity.Groups;
import com.himalaya.task.mapper.GroupMapper;
import com.himalaya.task.repo.GroupRepo;
import com.himalaya.task.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepo;

    public GroupServiceImpl(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    @Override
    public GroupDto create(GroupDto groupDto) {

        Groups groupsByGroupName = groupRepo.findGroupsByGroupName(groupDto.groupName());
        if(groupsByGroupName != null){
            throw new AlreadyExistsException("Group already exists");
        }
        Groups entity = GroupMapper.toEntity(groupDto);
        groupRepo.save(entity);
        return GroupMapper.toDto(entity);
    }

    @Override
    public List<GroupDto> getAll() {
        log.info("inside get all group : service");
        return groupRepo.findAll().stream().map(GroupMapper::toDto).collect(Collectors.toList());
    }
}
