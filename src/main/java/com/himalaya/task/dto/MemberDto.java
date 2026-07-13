package com.himalaya.task.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MemberDto(
         Integer id,

         @NotBlank(message = "full name is required")
         String fullName,

         @Email
         String email,

         String phoneNumber
) {
}
