package com.example.practice.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
public class MemberInsertDto {
    @NotBlank String email;
    @NotBlank String name;
}