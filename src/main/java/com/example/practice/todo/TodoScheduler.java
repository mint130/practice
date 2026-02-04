package com.example.practice.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TodoScheduler {
    private final TodoService todoService;

    @Transactional
    @Scheduled(cron = "0 */3 * * * *") // 매 3분 마다
    public void runDeadlineJob() {
        // deadline이 지난 할일 주기적으로 완료 처리
        int count = todoService.completeExpiredTodos();
        log.info("마감 기한이 지나 완료 처리된 todo 갯수: {}", count);
    }
}
