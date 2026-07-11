package com.himalaya.task.dto;

import lombok.Builder;

@Builder
public record MemberDto(
         Integer id,


         String firstName,

         String lastName,

         String fullName,

         String email,

         String phoneNumber
) {
}
