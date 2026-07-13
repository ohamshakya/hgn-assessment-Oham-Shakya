package com.himalaya.task.dto;

import com.himalaya.task.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GroupDto(

        Integer id,

        @NotBlank(message = "group name is required")
        String groupName,

        List<MemberDto> member,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
