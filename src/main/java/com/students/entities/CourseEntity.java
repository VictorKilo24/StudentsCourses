package com.students.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Курс")
@Entity(name = "course")
@Table(name = "courses")
public class CourseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "available_space")
    private int availableSpace;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "persons_courses", joinColumns = @JoinColumn(name = "courses_id"), inverseJoinColumns = @JoinColumn(name = "persons_id"))
    private Set<PersonEntity> persons;
}
