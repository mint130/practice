package com.example.practice.todo;

import com.example.practice.todo.dto.TodoSimpleResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 제목으로 전체 할 일 검색
    @Override
    public List<TodoSimpleResponseDto> findByTitleContaining(String keyword, Long memberId) {
        QTodo todo = QTodo.todo;

        return queryFactory
                .select(Projections.fields(TodoSimpleResponseDto.class,
                        todo.id,
                        todo.title,
                        todo.deadline,
                        todo.completed
                ))
                .from(todo)
                .where(
                        todo.title.contains(keyword),
                        todo.member.id.eq(memberId)
                )
                .orderBy(todo.createdAt.desc())
                .fetch();
    }

    // 전체 완료된 할 일 목록 반환
    @Override
    public List<TodoSimpleResponseDto> findCompletedTodos() {
        QTodo todo = QTodo.todo;

        return queryFactory
                .select(Projections.fields(TodoSimpleResponseDto.class,
                        todo.id,
                        todo.title,
                        todo.deadline,
                        todo.completed
                ))
                .from(todo)
                .where(todo.completed.eq(true))
                .orderBy(todo.createdAt.desc())
                .fetch();

    }

    // 전체 미완료 된 할 일 목록 반환
    @Override
    public List<TodoSimpleResponseDto> findIncompleteTodos() {
        QTodo todo = QTodo.todo;

        return queryFactory
                .select(Projections.fields(TodoSimpleResponseDto.class,
                        todo.id,
                        todo.title,
                        todo.deadline,
                        todo.completed
                ))
                .from(todo)
                .where(todo.completed.eq(false))
                .orderBy(todo.createdAt.desc())
                .fetch();
    }

    // 회원의 완료된 할 일 목록 반환
    @Override
    public List<TodoSimpleResponseDto> findCompletedTodosByMemberId(Long memberId) {
        QTodo todo = QTodo.todo;

        return queryFactory
                .select(Projections.fields(TodoSimpleResponseDto.class,
                        todo.id,
                        todo.title,
                        todo.deadline,
                        todo.completed
                ))
                .from(todo)
                .where(
                        todo.member.id.eq(memberId),
                        todo.completed.eq(true)
                )
                .orderBy(todo.createdAt.desc())
                .fetch();
    }
}