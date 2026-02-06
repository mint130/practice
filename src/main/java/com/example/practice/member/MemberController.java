package com.example.practice.member;

import com.example.practice.common.exception.ExceptionResponseDto;
import com.example.practice.member.dto.MemberInsertDto;
import com.example.practice.member.dto.MemberDetailResponseDto;
import com.example.practice.member.dto.MemberResponseDto;
import com.example.practice.common.exception.ErrorResponse;
import com.example.practice.member.dto.MemberUpdateDto;
import com.example.practice.todo.dto.TodoSimpleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "Member", description = "회원 관리 API")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @Operation(
            summary = "멤버 생성 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "회원을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "이미 존재하는 이메일입니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class)))
            }
    )
    public ResponseEntity<MemberResponseDto> addMember(@Valid @RequestBody MemberInsertDto request) {
        return ResponseEntity.ok(memberService.addMember(request));
    }

    @GetMapping("/{memberId}")
    @Operation(
            summary = "멤버 상세 조회 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "회원을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
            }
    )
    public ResponseEntity<MemberDetailResponseDto> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMember(memberId));
    }

    @PatchMapping("/{memberId}")
    @Operation(
            summary = "멤버 수정 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "회원을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
            }
    )
    public ResponseEntity<MemberDetailResponseDto> updateMember(@PathVariable Long memberId, @Valid @RequestBody MemberUpdateDto request) {
        return ResponseEntity.ok(memberService.updateMember(memberId, request));
    }

    @GetMapping("/{memberId}/todos")
    @Operation(
            summary = "멤버 할일 리스트 조회 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "회원을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
            }
    )
    public ResponseEntity<List<TodoSimpleResponseDto>> getTodos(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getTodos(memberId));
    }

    @GetMapping("/{memberId}/todos/completed")
    @Operation(
            summary = "멤버의 완료한 할일 리스트 조회 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "400", description = "회원을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class))),
            }
    )
    public ResponseEntity<List<TodoSimpleResponseDto>> getCompletedTodos(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getCompletedTodos(memberId));
    }

    @GetMapping("/{memberId}/todos/search")
    @Operation(
            summary = "할일 제목 검색 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공")
            }
    )
    public ResponseEntity<List<TodoSimpleResponseDto>> searchByTitle(@RequestParam String keyword, @PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.searchByTitle(keyword, memberId));
    }
}
