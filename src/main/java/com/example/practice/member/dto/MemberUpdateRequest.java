package com.example.practice.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberUpdateRequest {
    private String email;
    private String name;
}