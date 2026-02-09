package com.example.practice.message;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MessageDto {
    private String id;
    private String message;
    private LocalDateTime timestamp;
}