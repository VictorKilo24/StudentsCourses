package com.students.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class NonExistentIdCourseException extends EmptyResultDataAccessException {
    public NonExistentIdCourseException(int expectedSize) {
        super(expectedSize);
    }
}
