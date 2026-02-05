package com.example.practice.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.AssertTrue;

@Getter
@AllArgsConstructor
@Builder
public class MemberUpdateDto {
    private String email;
    private String name;

    @AssertTrue(message = "이메일은 공백일 수 없습니다")
    private boolean isEmailValid() {
        return email == null || !email.isBlank();
    }

    @AssertTrue(message = "이름은 공백일 수 없습니다")
    private boolean isNameValid() {
        return name == null || !name.isBlank();
    }
}