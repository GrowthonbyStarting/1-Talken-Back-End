package com.example.talken.feedback.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FeedbackError {

    INVALID_USER(HttpStatus.BAD_REQUEST, "피드백을 작성할 권한이 없습니다."),
    RESUME_NOT_FOUND(HttpStatus.NOT_FOUND, "이력서를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;

}
