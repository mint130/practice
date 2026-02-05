package com.example.practice.member;

import com.example.practice.common.exception.DuplicatedException;
import com.example.practice.common.exception.ErrorCode;
import com.example.practice.common.exception.NotFoundException;
import com.example.practice.member.dto.MemberInsertDto;
import com.example.practice.member.dto.MemberDetailResponseDto;
import com.example.practice.member.dto.MemberResponseDto;
import com.example.practice.member.dto.MemberUpdateDto;
import com.example.practice.todo.TodoRepository;
import com.example.practice.todo.dto.TodoSimpleResponseDto;
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

    // 회원 추가
    @Transactional
    public MemberResponseDto addMember(MemberInsertDto request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            //throw new MemberException(ErrorCode.MEMBER_EMAIL_DUPLICATED);
            throw new DuplicatedException("Duplicated email = " + request.getEmail());
        }
        Member member = Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();

        Member savedMember = memberRepository.save(member);
        return MemberResponseDto.builder().memberId(savedMember.getId()).build();
    }

    // 회원의 할 일 목록
    public List<TodoSimpleResponseDto> getTodos(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found member by idx = " + memberId));
        return todoRepository.findByMember(member)
                .stream()
                .map(todo -> TodoSimpleResponseDto.builder()
                        .id(todo.getId())
                        .title(todo.getTitle())
                        .deadline(todo.getDeadline())
                        .completed(todo.getCompleted())
                        .build())
                .collect(Collectors.toList());
    }

    public MemberDetailResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found member by idx = " + memberId));
        return MemberDetailResponseDto.from(member);
    }

    // 회원 정보 수정
    @Transactional
    public MemberDetailResponseDto updateMember(Long memberId, MemberUpdateDto request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found member by idx = " + memberId));
        if (request.getEmail() != null && request.getEmail().isBlank()) {
            throw new MemberException(ErrorCode.INVALID_INPUT_VALUE);
        }
        if (request.getName() != null && request.getName().isBlank()) {
            throw new MemberException(ErrorCode.INVALID_INPUT_VALUE);
        }
        member.update(request.getEmail(), request.getName());
        memberRepository.save(member);
        return MemberDetailResponseDto.from(member);
    }
}
