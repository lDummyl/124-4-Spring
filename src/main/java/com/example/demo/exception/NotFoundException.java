package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public NotFoundException(Long id) {
        super("Could not find data by " + id);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("Could not find data");
    }
}
