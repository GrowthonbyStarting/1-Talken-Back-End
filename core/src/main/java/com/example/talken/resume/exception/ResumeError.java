package com.example.talken.resume.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResumeError {
    INVALID_USER(HttpStatus.BAD_REQUEST, "이력서를 작성할 권한이 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
