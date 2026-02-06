package com.example.practice.todo;

import com.example.practice.todo.dto.TodoSimpleResponseDto;

import java.util.List;

public interface TodoRepositoryCustom {
    List<TodoSimpleResponseDto> findByTitleContaining(String keyword, Long memberId);
    List<TodoSimpleResponseDto> findCompletedTodos();
    List<TodoSimpleResponseDto> findIncompleteTodos();
    List<TodoSimpleResponseDto> findCompletedTodosByMemberId(Long memberId);
}