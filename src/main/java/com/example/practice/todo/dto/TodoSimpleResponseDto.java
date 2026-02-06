package com.example.practice.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TodoSimpleResponseDto {
    Long id;
    String title;
    LocalDateTime deadline;
    Boolean completed;
}