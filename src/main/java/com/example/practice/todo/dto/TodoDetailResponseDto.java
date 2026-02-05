package com.example.practice.todo.dto;

import com.example.practice.todo.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class TodoDetailResponseDto {
    String title;
    String content;
    LocalDateTime deadline;
    Boolean completed;
    LocalDateTime createdAt;

    public static TodoDetailResponseDto from(Todo todo) {
        return TodoDetailResponseDto.builder()
                .title(todo.getTitle())
                .content(todo.getContent())
                .deadline(todo.getDeadline())
                .completed(todo.getCompleted())
                .createdAt(todo.getCreatedAt())
                .build();
    }
}