package com.example.demo.exceptionHandler;

import com.example.demo.exceptions.ApiError;
import com.example.demo.exceptions.CarDriverException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;


@EnableWebMvc
@ControllerAdvice
public class CarDriverExceptionHandler {

    @ExceptionHandler({CarDriverException.class})
    public ResponseEntity<Object> handleCarDriverException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMismatchException(MethodArgumentTypeMismatchException ex){
        String error = ex.getName() + " should be of type " + Optional.ofNullable(ex.getRequiredType())
                .map(Class::getName)
                .orElse("unknown");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
