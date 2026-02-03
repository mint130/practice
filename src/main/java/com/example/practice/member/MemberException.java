package com.example.practice.member;

import com.example.practice.common.exception.CustomException;
import com.example.practice.common.exception.ErrorCode;

public class MemberException extends CustomException {
    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
