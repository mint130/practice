package com.example.practice.todo.dto;

import com.example.practice.todo.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class TodoSimpleResponse {
    Long id;
    String title;
    LocalDateTime deadline;
    Boolean completed;
}
