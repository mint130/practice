package com.example.practice.member;

import com.example.practice.member.dto.MemberRequest;
import com.example.practice.member.dto.MemberResponse;
import com.example.practice.common.exception.ErrorResponse;
import com.example.practice.todo.dto.TodoSimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                    @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "409", description = "이미 존재하는 이메일입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<MemberResponse> addMember(@RequestBody MemberRequest memberRequest) {
        return ResponseEntity.ok(memberService.addMember(memberRequest));
    }

    @GetMapping("/{memberId}/todos")
    @Operation(
            summary = "멤버 할일 리스트 조회 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    public ResponseEntity<List<TodoSimpleResponse>> getTodos(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getTodos(memberId));
    }
}
