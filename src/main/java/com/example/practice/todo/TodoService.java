package com.example.practice.todo;

import com.example.practice.common.exception.ErrorCode;
import com.example.practice.member.Member;
import com.example.practice.member.MemberException;
import com.example.practice.member.MemberRepository;

import com.example.practice.todo.dto.TodoAddRequest;
import com.example.practice.todo.dto.TodoDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository ;

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
        return TodoDetailResponse.builder()
                .title(todo.getTitle())
                .content(todo.getContent())
                .deadline(todo.getDeadline())
                .createdAt(todo.getCreatedAt())
                .completed(todo.getCompleted())
                .build();
    }

    public TodoDetailResponse getTodo(Long todoId){
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new TodoException(ErrorCode.TODO_NOT_FOUND));
        return TodoDetailResponse.builder()
                .title(todo.getTitle())
                .content(todo.getContent())
                .deadline(todo.getDeadline())
                .createdAt(todo.getCreatedAt())
                .completed(todo.getCompleted())
                .build();
    }

}
