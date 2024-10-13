package com.students.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class NonExistentIdPersonException extends EmptyResultDataAccessException {
    public NonExistentIdPersonException(int expectedSize) {
        super(expectedSize);
    }
}