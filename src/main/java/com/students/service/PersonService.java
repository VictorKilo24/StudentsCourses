package com.students.service;

import com.students.entities.PersonEntity;
import com.students.exceptions.NonExistentIdPersonException;
import com.students.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public PersonEntity getPersonById(int personId) {
        return personRepository.findById(personId).orElseThrow(() -> new NonExistentIdPersonException(0));
    }

}
