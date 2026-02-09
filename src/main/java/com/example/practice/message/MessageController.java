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

    private final MessageProducer messageProducer;

    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestParam String content) {
        MessageDto messageDto = MessageDto.builder()
                .message(content)
                .id(UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now())
                .build();
        messageProducer.sendMessage(messageDto);
        return ResponseEntity.ok().build();
    }

    // Direct Exchange 테스트
    @PostMapping("/direct")
    public ResponseEntity<Void> testDirect(@RequestParam String message) {
        messageProducer.sendDirectMessage(message);
        return ResponseEntity.ok().build();
    }

    // Topic Exchange 테스트 (매칭)
    @PostMapping("/topic/match")
    public ResponseEntity<Void> testTopicMatch(@RequestParam String message) {
        messageProducer.sendTopicMessageMatch(message);
        return ResponseEntity.ok().build();
    }

    // Topic Exchange 테스트 (매칭 안됨)
    @PostMapping("/topic/no-match")
    public ResponseEntity<Void> testTopicNoMatch(@RequestParam String message) {
        messageProducer.sendTopicMessageNoMatch(message);
        return ResponseEntity.ok().build();
    }

    // Fanout Exchange 테스트
    @PostMapping("/fanout")
    public ResponseEntity<Void> testFanout(@RequestParam String message) {
        messageProducer.sendFanoutMessage(message);
        return ResponseEntity.ok().build();
    }

    // 모든 Exchange 테스트
    @PostMapping("/all")
    public ResponseEntity<Void> testAll(@RequestParam String message) {
        messageProducer.sendToAll(message);
        return ResponseEntity.ok().build();
    }
}