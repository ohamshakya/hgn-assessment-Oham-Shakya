package com.himalaya.task.controller;


import com.himalaya.task.common.util.ResponseWrapper;
import com.himalaya.task.dto.GroupDto;
import com.himalaya.task.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@Slf4j
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseWrapper<GroupDto> create(@RequestBody GroupDto groupDto){
        log.info("inside create group : controller");
        GroupDto created = groupService.create(groupDto);
        return new ResponseWrapper<>(created,"created", HttpStatus.OK.value(),true);
    }

    @GetMapping
    public ResponseWrapper<List<GroupDto>> getAll(){
        log.info("inside get all group : controller");
        List<GroupDto> getAllResponse = groupService.getAll();
        return new ResponseWrapper<>(getAllResponse,"retrieved successfully",HttpStatus.OK.value(), true);
    }


}
