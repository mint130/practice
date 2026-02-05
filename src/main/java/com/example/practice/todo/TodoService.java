package com.example.practice.todo;

import com.example.practice.common.exception.ErrorCode;
import com.example.practice.member.Member;
import com.example.practice.member.MemberException;
import com.example.practice.member.MemberRepository;

import com.example.practice.todo.dto.TodoAddRequest;
import com.example.practice.todo.dto.TodoDetailResponse;
import com.example.practice.todo.dto.TodoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository ;

    // 할 일 생성
    @Transactional
    public TodoDetailResponse addTodo(Long memberId, TodoAddRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

        Todo todo = Todo.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .deadline(request.getDeadline())
                .build();
        todoRepository.save(todo);
        return TodoDetailResponse.from(todo);
    }

    // 할 일 조회
    public TodoDetailResponse getTodo(Long todoId){
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new TodoException(ErrorCode.TODO_NOT_FOUND));
        return TodoDetailResponse.from(todo);
    }

    // 할 일 삭제
    @Transactional
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new TodoException(ErrorCode.TODO_NOT_FOUND));
        todoRepository.delete(todo);
    }

    // 할 일 완료 <> 미완료 전환
    @Transactional
    public TodoDetailResponse toggleTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new TodoException(ErrorCode.TODO_NOT_FOUND));
        todo.toggle();
        return TodoDetailResponse.from(todo);
    }

    // 할 일 수정
    @Transactional
    public TodoDetailResponse updateTodo(Long todoId, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new TodoException(ErrorCode.TODO_NOT_FOUND));
        if (request.getTitle() != null && request.getTitle().isBlank()) {
            throw new TodoException(ErrorCode.INVALID_INPUT_VALUE);
        }
        if (request.getContent() != null && request.getContent().isBlank()) {
            throw new TodoException(ErrorCode.INVALID_INPUT_VALUE);
        }
        todo.update(request.getTitle(), request.getContent(), request.getDeadline());
        return TodoDetailResponse.from(todo);
    }

    // 일정 시간마다 deadline 지났고 완료되지 않은 할 일 완료 처리
    @Transactional
    public int completeExpiredTodos() {
        return todoRepository.completeExpireTodos(LocalDateTime.now());
    }
}
