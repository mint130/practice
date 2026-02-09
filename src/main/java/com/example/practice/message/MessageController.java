package com.example.practice.message;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
@Tag(name = "Message", description = "메시지큐 테스트 API")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/send")
    public ResponseEntity<Void> send(@RequestParam String content) {
        MessageDto messageDto = MessageDto.builder()
                .message(content)
                .id(UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now())
                .build();
        messageService.sendMessage(messageDto);
        return ResponseEntity.ok().build();
    }
}