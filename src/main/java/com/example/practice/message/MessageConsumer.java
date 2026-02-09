package com.example.practice.message;

import com.example.practice.common.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE1)
    public void receiveFromQueue1(String message) {
        log.info("From Queue 1: {}", message);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE2)
    public void receiveFromQueue2(String message) {
        log.info("From Queue 2: {}", message);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE3)
    public void receiveMessage(MessageDto messageDto) {
        log.info("From Queue 3: {}", messageDto);
    }
}