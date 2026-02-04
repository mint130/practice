package com.example.practice.member.dto;

import com.example.practice.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberDetailResponse {
    String email;
    String name;

    public static MemberDetailResponse from(Member member) {
        return MemberDetailResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }
}