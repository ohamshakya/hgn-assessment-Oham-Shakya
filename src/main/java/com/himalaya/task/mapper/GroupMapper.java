package com.himalaya.task.mapper;

import com.himalaya.task.dto.GroupDto;
import com.himalaya.task.dto.MemberDto;
import com.himalaya.task.entity.Groups;
import com.himalaya.task.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class GroupMapper {

    public static Groups toEntity(GroupDto groupDto){
        Groups groups = new Groups();
        groups.setGroupName(groupDto.groupName());
        List<Member> member = groupDto.member().stream().map(memberDto -> {Member member1 = new Member();
            member1.setFirstName(memberDto.firstName());
            member1.setLastName(memberDto.lastName());
            member1.setFullName(memberDto.fullName());
            member1.setEmail(memberDto.email());
            member1.setPhoneNumber(memberDto.phoneNumber());
        member1.setGroups(groups);

        return member1;
        }).toList();
        groups.setMember(member);
        return groups;
    }

    public static GroupDto toDto(Groups groups){
        List<MemberDto> member = new ArrayList<>();
        for(Member i : groups.getMember()){
            MemberDto dto = GroupMapper.toDto(i);
            member.add(dto);
        }
        return GroupDto.builder()
                .id(groups.getId())
                .groupName(groups.getGroupName())
                .member(member)
                .createdAt(groups.getCreatedAt())
                .updatedAt(groups.getUpdatedAt())
                .build();
    }

    public static MemberDto toDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
