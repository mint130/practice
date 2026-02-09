package com.example.practice.message;

import com.example.practice.common.config.RabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {
    private final RabbitTemplate rabbitTemplate;

    // Direct Exchange - 정확히 매칭
    public void sendDirectMessage(String message) {
        log.info("Direct Exchange: {}", message);
        rabbitTemplate.convertAndSend(
                RabbitConfig.DIRECT_EXCHANGE,
                RabbitConfig.DIRECT_KEY,
                message
        );
    }

    // Topic Exchange - 매칭 되는 패턴
    public void sendTopicMessageMatch(String message) {
        log.info("Topic Exchange: {}", message);
        rabbitTemplate.convertAndSend(
                RabbitConfig.TOPIC_EXCHANGE,
                "pattern.test",
                message
        );
    }

    // Topic Exchange 테스트 - 매칭 안되는 패턴
    public void sendTopicMessageNoMatch(String message) {
        log.info("Sending to Topic Exchange (no match): {}", message);
        rabbitTemplate.convertAndSend(
                RabbitConfig.TOPIC_EXCHANGE,
                "other.test",  // "pattern.*" 패턴에 매칭 안됨
                message
        );
    }

    public void sendFanoutMessage(String message) {
        log.info("Sending to Fanout Exchange: {}", message);
        rabbitTemplate.convertAndSend(
                RabbitConfig.FANOUT_EXCHANGE,
                "",  // Fanout은 routing key 무시
                message
        );
    }

    // 모든 Exchange에 메시지 보내기
    public void sendToAll(String message) {
        sendDirectMessage("Direct: " + message);
        sendTopicMessageMatch("Topic Match: " + message);
        sendTopicMessageNoMatch("Topic No Match: " + message);
        sendFanoutMessage("Fanout: " + message);
    }

    // dto 보내기
    public void sendMessage(MessageDto messageDto) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.TODO_EXCHANGE,
                RabbitConfig.TODO_KEY,
                messageDto
        );
    }
}