package com.students.service;

import com.students.entities.PersonEntity;
import com.students.exceptions.NonExistentIdPersonException;
import com.students.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    @DisplayName("Проверка запроса сущности студента")
    void testGetPersonById() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(1);
        personEntity.setFirstName("Ivan");
        when(personRepository.findById(1)).thenReturn(Optional.of(personEntity));
        PersonEntity result = personService.getPersonById(1);
        Assertions.assertEquals("Ivan", result.getFirstName());
        Assertions.assertEquals(1, result.getId());
    }

    @Test
    @DisplayName("Проверка запроса сущности студента при неправильном id")
    void testGetPersonByNotExistId() {
        when(personRepository.findById(0)).thenThrow(new NonExistentIdPersonException(0));
        Assertions.assertThrows(NonExistentIdPersonException.class, () -> personService.getPersonById(0));
    }
}
