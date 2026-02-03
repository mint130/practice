package com.example.practice.todo;

import com.example.practice.common.exception.ErrorResponse;
import com.example.practice.todo.dto.TodoAddRequest;
import com.example.practice.todo.dto.TodoDetailResponse;
import com.example.practice.todo.dto.TodoRequest;
import com.example.practice.todo.dto.TodoSimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
@Tag(name = "Todo", description = "할일 관리 API")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    @Operation(
            summary = "할일 생성 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    public ResponseEntity<TodoDetailResponse> addTodo(Long memberId, TodoAddRequest request) {
        return ResponseEntity.ok(todoService.addTodo(memberId, request));
    }

    @GetMapping("/{todoId}")
    @Operation(
            summary = "할일 상세 조회 API",
            responses = {
                    @ApiResponse(responseCode = "404", description = "할 일을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    public ResponseEntity<TodoDetailResponse> getTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }
}
