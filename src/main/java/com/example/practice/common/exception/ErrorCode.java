package com.example.practice.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(NOT_FOUND, "회원을 찾을 수 없습니다."),
    MEMBER_EMAIL_DUPLICATED(CONFLICT, "이미 존재하는 이메일입니다."),

    TODO_NOT_FOUND(NOT_FOUND, "할 일을 찾을 수 없습니다."),

    INVALID_INPUT_VALUE(BAD_REQUEST, "잘못된 입력값입니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
