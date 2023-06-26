package com.example.talken.feedback.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FeedbackException extends RuntimeException {

    @Getter
    private final FeedbackError error;
}
