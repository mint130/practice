package com.example.practice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionResponseDto {
    private String message;
    private Object result;
}