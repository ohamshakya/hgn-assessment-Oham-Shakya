package com.himalaya.task.controller;


import com.himalaya.task.common.util.ResponseWrapper;
import com.himalaya.task.dto.GroupDto;
import com.himalaya.task.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        GroupDto groupDto1 = groupService.create(groupDto);
        return new ResponseWrapper<>(groupDto1,"created", HttpStatus.OK.value(),true);
    }
}
