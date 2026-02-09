package com.example.practice.todo;

import com.example.practice.member.dto.MemberQueryDto;
import com.example.practice.todo.dto.TodoSimpleResponseDto;

import java.util.List;

public interface TodoRepositoryCustom {
    List<TodoSimpleResponseDto> findAll(MemberQueryDto queryDto);
}