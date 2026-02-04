package com.example.practice.todo.dto;

import com.example.practice.todo.Todo;
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

    public static TodoDetailResponse from(Todo todo) {
        return TodoDetailResponse.builder()
                .title(todo.getTitle())
                .content(todo.getContent())
                .deadline(todo.getDeadline())
                .completed(todo.getCompleted())
                .createdAt(todo.getCreatedAt())
                .build();
    }
}