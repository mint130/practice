package com.example.practice.todo;

import com.example.practice.common.exception.ErrorCode;
import com.example.practice.common.exception.NotFoundException;
import com.example.practice.member.Member;
import com.example.practice.member.MemberException;
import com.example.practice.member.MemberRepository;

import com.example.practice.todo.dto.TodoInsertDto;
import com.example.practice.todo.dto.TodoDetailResponseDto;
import com.example.practice.todo.dto.TodoUpdateDto;
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
    public TodoDetailResponseDto addTodo(Long memberId, TodoInsertDto request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found member by idx =" + memberId));

        Todo todo = Todo.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .deadline(request.getDeadline())
                .build();
        todoRepository.save(todo);
        return TodoDetailResponseDto.from(todo);
    }

    // 할 일 조회
    public TodoDetailResponseDto getTodo(Long todoId){
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new NotFoundException("Not Found todo by idx =" + todoId));
        return TodoDetailResponseDto.from(todo);
    }

    // 할 일 삭제
    @Transactional
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new NotFoundException("Not Found todo by idx =" + todoId));
        todoRepository.delete(todo);
    }

    // 할 일 완료 <> 미완료 전환
    @Transactional
    public TodoDetailResponseDto toggleTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new TodoException(ErrorCode.TODO_NOT_FOUND));
        todo.toggle();
        return TodoDetailResponseDto.from(todo);
    }

    // 할 일 수정
    @Transactional
    public TodoDetailResponseDto updateTodo(Long todoId, TodoUpdateDto request) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new NotFoundException("Not Found todo by idx =" + todoId));
        /* if (request.getTitle() != null && request.getTitle().isBlank()) {
            throw new TodoException(ErrorCode.INVALID_INPUT_VALUE);
        }
        if (request.getContent() != null && request.getContent().isBlank()) {
            throw new TodoException(ErrorCode.INVALID_INPUT_VALUE);
        } */
        todo.update(request.getTitle(), request.getContent(), request.getDeadline());
        return TodoDetailResponseDto.from(todo);
    }

    // 일정 시간마다 deadline 지났고 완료되지 않은 할 일 완료 처리
    @Transactional
    public int completeExpiredTodos() {
        return todoRepository.completeExpireTodos(LocalDateTime.now());
    }
}
