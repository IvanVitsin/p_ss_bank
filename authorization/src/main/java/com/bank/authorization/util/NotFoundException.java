package com.bank.authorization.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Обработка исключения not found
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
