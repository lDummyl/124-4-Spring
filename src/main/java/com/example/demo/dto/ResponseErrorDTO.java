package com.example.demo.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author chervinko <br>
 * 30.07.2021
 */
@Data
public class ResponseErrorDTO {
    private final HttpStatus status;
    private final String message;
}
