package com.example.talken.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserException extends RuntimeException {

    @Getter
    private final UserError userError;
}
