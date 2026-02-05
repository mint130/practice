package com.example.practice.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class TodoSimpleResponseDto {
    Long id;
    String title;
    LocalDateTime deadline;
    Boolean completed;
}
