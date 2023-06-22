package com.example.talken.resume.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ResumeException extends RuntimeException {

    @Getter
    private final ResumeError error;
}
