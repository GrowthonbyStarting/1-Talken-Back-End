package com.example.talken.common.security.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SecurityException {

    private int code;
    private String message;

    public SecurityException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
