package com.example.practice.todo;

import com.example.practice.common.exception.CustomException;
import com.example.practice.common.exception.ErrorCode;

public class TodoException extends CustomException {
    public TodoException(ErrorCode errorCode){
        super(errorCode);
    }
}
