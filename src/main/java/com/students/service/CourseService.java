package com.students.service;

import com.students.entities.CourseEntity;
import com.students.entities.PersonEntity;
import com.students.exceptions.NonExistentIdCourseException;
import com.students.exceptions.NonVacanciesAvailableException;
import com.students.exceptions.StudentIsAlreadyEnrolledException;
import com.students.exceptions.UnavailableTimeException;
import com.students.repositories.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final PersonService personService;

    public CourseEntity getCourse(int courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new NonExistentIdCourseException(0));
    }

    public List<CourseEntity> getAllCourses() {
        return (List<CourseEntity>) courseRepository.findAll();
    }

    @Transactional
    public String addPersonToCourse(int personId, int courseId) {

        if (LocalTime.now().isBefore(LocalTime.of(6,0))
        || LocalTime.now().isAfter(LocalTime.of(22,0))) {
            throw new UnavailableTimeException();
        }

        CourseEntity courseEntity = getCourse(courseId);
        PersonEntity personEntity = personService.getPersonById(personId);


        synchronized (courseEntity) {
            int amount = courseEntity.getAvailableSpace();
            if (courseEntity.getAvailableSpace() == 0) {
                throw new NonVacanciesAvailableException();
            }
            amount--;
            if (courseEntity.getPersons().contains(personEntity)) {
                throw new StudentIsAlreadyEnrolledException();
            }
            courseEntity.getPersons().add(personEntity);
            courseEntity.setAvailableSpace(amount);
            courseRepository.save(courseEntity);
            return "Студент успешно записан на курс";
        }
    }
}
