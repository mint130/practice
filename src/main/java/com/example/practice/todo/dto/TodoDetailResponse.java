package com.example.practice.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class TodoDetailResponse {
    String title;
    String content;
    LocalDateTime deadline;
    Boolean completed;
    LocalDateTime createdAt;
}
