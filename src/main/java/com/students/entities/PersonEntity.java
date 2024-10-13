package com.students.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Пользователь")
@Entity(name = "person")
@Table(name = "persons")
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(name = "persons_courses", joinColumns = @JoinColumn(name = "persons_id"), inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private Set<CourseEntity> courses;
}
