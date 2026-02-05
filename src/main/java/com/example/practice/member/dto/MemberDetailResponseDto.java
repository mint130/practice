package com.example.practice.member.dto;

import com.example.practice.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberDetailResponseDto {
    String email;
    String name;

    public static MemberDetailResponseDto from(Member member) {
        return MemberDetailResponseDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }
}