package com.example.demo.ex;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TypicalError {

    NOT_FOUND(HttpStatus.BAD_REQUEST), UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;

    TypicalError(HttpStatus status) {
        this.status = status;
    }
}
