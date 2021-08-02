package com.example.demo.services.crud.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author chervinko <br>
 * 30.07.2021
 */
@Getter
@RequiredArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    private final Class<?> foundClass;
    private final Long identify;
}
