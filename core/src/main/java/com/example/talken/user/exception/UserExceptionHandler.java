package com.example.talken.user.exception;

import com.example.talken.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice(basePackages = {"com.example.talken.user.controller"})
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> userExceptionHandler(UserException e) {
        return Response.<Void>builder()
                .code(e.getUserError().getStatus().value())
                .message(e.getUserError().getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> exceptionHandler(Exception e) {
        return Response.<Void>builder()
                .code(UserError.INTERNAL_SERVER_ERROR.getStatus().value())
                .message(e.getMessage())
                .build();
    }
}
