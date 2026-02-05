package com.example.practice.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class TodoUpdateDto {
    String title;
    String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline;
}