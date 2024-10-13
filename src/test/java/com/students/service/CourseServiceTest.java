package com.students.service;

import com.students.entities.CourseEntity;
import com.students.entities.PersonEntity;
import com.students.exceptions.NonExistentIdCourseException;
import com.students.exceptions.NonVacanciesAvailableException;
import com.students.exceptions.StudentIsAlreadyEnrolledException;
import com.students.repositories.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @Mock
    private PersonService personService;

    @InjectMocks
    private CourseService courseService;


    @Test
    @DisplayName("Проверка запроса сущности студента")
    void testGetPersonById() {
        CourseEntity courseEntity = new CourseEntity();
        PersonEntity personEntity = new PersonEntity();
        Set<PersonEntity> personSet = Set.of(personEntity);
        courseEntity.setId(1);
        courseEntity.setName("Maths");
        courseEntity.setPersons(personSet);
        when(courseRepository.findById(1)).thenReturn(Optional.of(courseEntity));
        CourseEntity result = courseService.getCourse(1);
        Assertions.assertEquals("Maths", result.getName());
        Assertions.assertEquals(1, result.getPersons().size());
    }

    @Test
    @DisplayName("Проверка запроса сущности студента при неправильном id")
    void testGetPersonByNotExistId() {
        when(courseRepository.findById(0)).thenThrow(new NonExistentIdCourseException(0));
        Assertions.assertThrows(NonExistentIdCourseException.class, () -> courseService.getCourse(0));
    }

    @Test
    @DisplayName("Проверка записи студента, если он уже записан")
    void testAddPersonToCourseIfStudentIsAlreadyEnrolled() {
        CourseEntity courseEntity = new CourseEntity();
        PersonEntity personEntity = new PersonEntity();
        Set<PersonEntity> personSet = Set.of(personEntity);
        courseEntity.setId(1);
        courseEntity.setAvailableSpace(1);
        courseEntity.setName("Maths");
        courseEntity.setPersons(personSet);
        when(courseRepository.findById(1)).thenReturn(Optional.of(courseEntity));
        when(personService.getPersonById(1)).thenReturn(personEntity);
        Assertions.assertThrows(StudentIsAlreadyEnrolledException.class, () -> courseService.addPersonToCourse(1, 1));
    }

    @Test
    @DisplayName("Проверка записи студента, если свободных мест нет")
    void testAddPersonToCourseUnavailableTime() {
        CourseEntity courseEntity = new CourseEntity();
        PersonEntity personEntity = new PersonEntity();
        Set<PersonEntity> personSet = Set.of(personEntity);
        courseEntity.setId(1);
        courseEntity.setAvailableSpace(0);
        courseEntity.setName("Maths");
        courseEntity.setPersons(personSet);
        when(courseRepository.findById(1)).thenReturn(Optional.of(courseEntity));
        when(personService.getPersonById(1)).thenReturn(personEntity);
        Assertions.assertThrows(NonVacanciesAvailableException.class, () -> courseService.addPersonToCourse(1, 1));
    }

    @Test
    @DisplayName("Проверка успешной записи студента на курс")
    void testAddPersonToCourseOk() {
        CourseEntity courseEntity = new CourseEntity();
        PersonEntity personEntity = new PersonEntity();
        Set<PersonEntity> personSet = new HashSet<>();
        courseEntity.setId(1);
        courseEntity.setAvailableSpace(1);
        courseEntity.setName("Maths");
        courseEntity.setPersons(personSet);
        when(courseRepository.findById(1)).thenReturn(Optional.of(courseEntity));
        when(personService.getPersonById(1)).thenReturn(personEntity);
        String result = courseService.addPersonToCourse(1, 1);
        Assertions.assertEquals("Студент успешно записан на курс", result);
    }
}
