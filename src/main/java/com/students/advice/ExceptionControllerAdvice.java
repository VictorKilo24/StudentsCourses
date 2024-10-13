package com.students.advice;


import com.students.exceptions.*;
import com.students.exceptions.errors.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NonExistentIdCourseException.class)
    public ResponseEntity<ErrorDetails> NonExistentIdCourseException() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Такого курса не существует");
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(NonExistentIdPersonException.class)
    public ResponseEntity<ErrorDetails> NonExistentIdPersonException() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Такого студента не существует");
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(NonVacanciesAvailableException.class)
    public ResponseEntity<ErrorDetails> NonVacanciesAvailableException() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("На курсе не осталось свободных мест");
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(StudentIsAlreadyEnrolledException.class)
    public ResponseEntity<ErrorDetails> StudentIsAlreadyEnrolledException() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Студент уже записан на курс");
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(UnavailableTimeException.class)
    public ResponseEntity<ErrorDetails> UnavailableTimeException() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Недоступное время для записи на курс");
        return ResponseEntity.badRequest().body(errorDetails);
    }
}