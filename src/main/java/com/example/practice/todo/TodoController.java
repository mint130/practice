package com.example.practice.todo;

import com.example.practice.common.exception.ErrorResponse;
import com.example.practice.common.exception.ExceptionResponseDto;
import com.example.practice.todo.dto.TodoInsertDto;
import com.example.practice.todo.dto.TodoDetailResponseDto;
import com.example.practice.todo.dto.TodoUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                    @ApiResponse(responseCode = "400", description = "회원을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
            }
    )
    public ResponseEntity<TodoDetailResponseDto> addTodo(@RequestParam Long memberId, @Valid @RequestBody TodoInsertDto request) {
        return ResponseEntity.ok(todoService.addTodo(memberId, request));
    }

    @GetMapping("/{todoId}")
    @Operation(
            summary = "할일 상세 조회 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "할 일을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
            }
    )
    public ResponseEntity<TodoDetailResponseDto> getTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }

    @DeleteMapping("/{todoId}")
    @Operation(
            summary = "할일 삭제 API",
            responses = {
                    @ApiResponse(responseCode = "204", description = "삭제 성공"),
                    @ApiResponse(responseCode = "400", description = "할 일을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
            }
    )
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{todoId}/toggle")
    @Operation(
            summary = "할일 완료, 미완료 전환 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "할 일을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
            }
    )
    public ResponseEntity<TodoDetailResponseDto> toggleTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.toggleTodo(todoId));
    }

    @PatchMapping("/{todoId}")
    @Operation(
            summary = "할일 수정 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400`", description = "할 일을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
            }
    )
    public ResponseEntity<TodoDetailResponseDto> updateTodo(@PathVariable Long todoId, @Valid @RequestBody TodoUpdateDto request) {
        return ResponseEntity.ok(todoService.updateTodo(todoId, request));
    }
}
