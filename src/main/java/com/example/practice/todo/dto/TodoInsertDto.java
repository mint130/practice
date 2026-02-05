package com.example.practice.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class TodoInsertDto {
    @NotNull String title;
    @NotNull String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline;
}
