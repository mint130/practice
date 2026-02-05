package com.example.practice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DuplicatedException extends RuntimeException {
    final String information;
}