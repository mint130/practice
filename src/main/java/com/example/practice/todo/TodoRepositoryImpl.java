package com.example.practice.todo;

import com.example.practice.member.dto.MemberQueryDto;
import com.example.practice.todo.dto.TodoSimpleResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.practice.todo.QTodo.todo;

@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // memberId, 제목, 완료 여부로 검색
    @Override
    public List<TodoSimpleResponseDto> findAll(MemberQueryDto queryDto) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(todo.member.id.eq(queryDto.getMemberId()));

        if (queryDto.getIsCompleted() != null) {
            if(!queryDto.getIsCompleted()) { booleanBuilder.and(todo.completed.eq(false));}
            else {
                booleanBuilder.and(todo.completed.eq(true));
            }
        }

        if (!queryDto.getKeyword().isEmpty()) {
            booleanBuilder.and(todo.title.contains(queryDto.getKeyword()));
        }

        List<TodoSimpleResponseDto> results = queryFactory.select(Projections.fields(TodoSimpleResponseDto.class,
                        todo.id,
                        todo.title,
                        todo.deadline,
                        todo.completed
                ))
                .from(todo)
                .where(booleanBuilder)
                .orderBy(todo.createdAt.desc())
                .fetch();

        return results;
    }
}