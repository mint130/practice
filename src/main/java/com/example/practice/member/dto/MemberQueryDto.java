package com.example.practice.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class MemberQueryDto {
    @NotNull
    private Long memberId;

    private String keyword;

    private Boolean isCompleted;
}