package com.example.talken.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Response<T> {

    @Builder.Default
    Integer code = HttpStatus.OK.value();

    @Builder.Default
    String message = HttpStatus.OK.getReasonPhrase();

    T data;

    public ResponseEntity<Response<T>> response(Integer code, T data) {
        return ResponseEntity
                .status(code)
                .body(Response.<T>builder()
                        .data(data)
                        .build()
                );
    }
}
