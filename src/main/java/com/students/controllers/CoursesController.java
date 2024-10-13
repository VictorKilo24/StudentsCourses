package com.students.controllers;

import com.students.entities.CourseEntity;
import com.students.entities.PersonEntity;
import com.students.exceptions.NonExistentIdCourseException;
import com.students.exceptions.NonExistentIdPersonException;
import com.students.exceptions.NonVacanciesAvailableException;
import com.students.exceptions.StudentIsAlreadyEnrolledException;
import com.students.repositories.CourseRepository;
import com.students.repositories.PersonRepository;
import com.students.service.CourseService;
import com.students.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Курсы", description = "Сервис записи на студенческие курсы")
@RequiredArgsConstructor
public class CoursesController {

    private final CourseService courseService;


    @Operation(summary = "Вывести список всех курсов",
            description = "Позволяет вывести список всех курсов с актуальным количеством свободных мест")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        List<CourseEntity> listCourses = courseService.getAllCourses();
        return ResponseEntity.ok(listCourses);
    }

    @Operation(summary = "Записать студента на курс",
            description = "Позволяет записать студента на курс по id ")
    @PostMapping("/person")
    @ResponseStatus(HttpStatus.OK)
    public synchronized ResponseEntity<String> addPersonToCourse(@RequestParam("person_id") Integer personId,
                                                                 @RequestParam("course_id") Integer courseId) {
        String response = courseService.addPersonToCourse(personId, courseId);
        return ResponseEntity.ok(response);
    }
}
