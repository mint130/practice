package com.example.practice.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class TodoUpdateDto {
    String title;
    String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline;

    @AssertTrue(message = "제목은 공백일 수 없습니다")
    private boolean isTitleValid() {
        return title == null || !title.isBlank();
    }

    @AssertTrue(message = "내용은 공백일 수 없습니다")
    private boolean isContentValid() {
        return content == null || !content.isBlank();
    }
}