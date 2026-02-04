package com.example.practice.member;

import com.example.practice.common.exception.ErrorCode;
import com.example.practice.member.dto.MemberAddRequest;
import com.example.practice.member.dto.MemberDetailResponse;
import com.example.practice.member.dto.MemberResponse;
import com.example.practice.member.dto.MemberUpdateRequest;
import com.example.practice.todo.TodoRepository;
import com.example.practice.todo.dto.TodoSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public MemberResponse addMember(MemberAddRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(ErrorCode.MEMBER_EMAIL_DUPLICATED);
        }
        Member member = Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();

        Member savedMember = memberRepository.save(member);
        return MemberResponse.builder().memberId(savedMember.getId()).build();
    }

    public List<TodoSimpleResponse> getTodos(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        return todoRepository.findByMember(member)
                .stream()
                .map(todo -> TodoSimpleResponse.builder()
                        .id(todo.getId())
                        .title(todo.getTitle())
                        .deadline(todo.getDeadline())
                        .completed(todo.getCompleted())
                        .build())
                .collect(Collectors.toList());
    }

    public MemberDetailResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        return MemberDetailResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }

    @Transactional
    public MemberDetailResponse updateMember(Long memberId, MemberUpdateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        if (request.getEmail() != null && request.getEmail().isBlank()) {
            throw new MemberException(ErrorCode.INVALID_INPUT_VALUE);
        }
        if (request.getName() != null && request.getName().isBlank()) {
            throw new MemberException(ErrorCode.INVALID_INPUT_VALUE);
        }
        member.update(request.getEmail(), request.getName());
        memberRepository.save(member);
        return MemberDetailResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }
}
