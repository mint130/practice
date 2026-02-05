package com.example.practice.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // global error exception
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponseDto> exceptionHandle(Exception e) {
        log.error("Server Global Error=");
        log.error(e.toString());

        ExceptionResponseDto exceptionResponseDTO = ExceptionResponseDto.builder()
                .message("Server error")
                .result(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDTO);
    }


    // not found exception
    @ExceptionHandler(value=NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> notFoundExceptionHandler(NotFoundException e){
        log.error("Not found exception");
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .message("Not found exception")
                .result(e.getInformation())
                .build();
        return ResponseEntity.badRequest().body(exceptionResponseDto);
    }

    // 중복 가입 exception
    @ExceptionHandler(value = DuplicatedException.class)
    public ResponseEntity<ExceptionResponseDto> duplicatedExceptionHandler(DuplicatedException e){
        log.error("Duplicated exception");
        ExceptionResponseDto exceptionResponseDTO = ExceptionResponseDto.builder()
                .message("Duplicated exception")
                .result(e.getInformation())
                .build();

        return ResponseEntity.badRequest().body(exceptionResponseDTO);
    }

    // @Valid exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e) {
        log.error("Validation exception");

        // 첫 번째 에러의 필드와 메시지를 가져오기
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " = " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        ExceptionResponseDto exceptionResponseDTO = ExceptionResponseDto.builder()
                .message("Validation exception")
                .result(errorMessage)
                .build();

        return ResponseEntity.badRequest().body(exceptionResponseDTO);
    }

    /*
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        log.error("CustomException 발생: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(errorCode)
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        log.error("ValidationException: {}", e.getMessage());
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.INVALID_INPUT_VALUE)
                .message(message)
                .build();
        return ResponseEntity.status(ErrorCode.INVALID_INPUT_VALUE.getHttpStatus()).body(response);
    }
    */
}
