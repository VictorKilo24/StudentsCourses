package com.students.repositories;


import com.students.entities.CourseEntity;
import org.springframework.data.repository.CrudRepository;


public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
}

