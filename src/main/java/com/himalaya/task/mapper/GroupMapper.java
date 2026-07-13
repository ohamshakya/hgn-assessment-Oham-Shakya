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
        List<Member> member = groupDto.member().stream().map(memberDto -> {Member members = new Member();
            members.setFullName(memberDto.fullName());
            members.setEmail(memberDto.email());
            members.setPhoneNumber(memberDto.phoneNumber());
        members.setGroups(groups);

        return members;
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
                .fullName(member.getFullName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
