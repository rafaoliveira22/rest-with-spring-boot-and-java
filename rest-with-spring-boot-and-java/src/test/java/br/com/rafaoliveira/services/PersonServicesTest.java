package br.com.rafaoliveira.services;

import br.com.rafaoliveira.data.dto.v1.PersonDTOV1;
import br.com.rafaoliveira.exceptions.RequiredObjectIsNullException;
import br.com.rafaoliveira.model.Person;
import br.com.rafaoliveira.repositories.PersonRepository;
import br.com.rafaoliveira.unittests.mapper.mocks.MockPerson;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("self")
                                && link.getHref().endsWith("/api/person/v1/1")
                                && link.getType().equals("GET")
        ));

        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("findAll")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("GET")
                ));

        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("create")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("POST")
                ));

        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("update")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("PUT")
                ));

        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("delete")
                                && link.getHref().endsWith("/api/person/v1/1")
                                && link.getType().equals("DELETE")
                ));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void create() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTOV1 dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.create(null);
                });

        String expecttedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expecttedMessage));
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);

        Person persisted = person;
        persisted.setId(1L);

        PersonDTOV1 dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("self")
                                && link.getHref().endsWith("/api/person/v1/1")
                                && link.getType().equals("GET")
                ));

        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("findAll")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("GET")
                ));

        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("create")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("POST")
                ));

        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("update")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("PUT")
                ));

        assertNotNull(
                result.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("delete")
                                && link.getHref().endsWith("/api/person/v1/1")
                                && link.getType().equals("DELETE")
                ));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }


    @Test
    void testUpdateWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.update(null);
                });

        String expecttedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expecttedMessage));
    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findByAll() {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTOV1> people = service.findByAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getLinks());
        assertNotNull(
                personOne.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("self")
                                && link.getHref().endsWith("/api/person/v1/1")
                                && link.getType().equals("GET")
                ));

        assertNotNull(
                personOne.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("findAll")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("GET")
                ));

        assertNotNull(
                personOne.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("create")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("POST")
                ));

        assertNotNull(
                personOne.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("update")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("PUT")
                ));

        assertNotNull(
                personOne.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("delete")
                                && link.getHref().endsWith("/api/person/v1/1")
                                && link.getType().equals("DELETE")
                ));

        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personFourth = people.get(4);
        assertNotNull(personFourth);
        assertNotNull(personFourth.getId());
        assertNotNull(personFourth.getLinks());
        assertNotNull(
                personFourth.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("self")
                                && link.getHref().endsWith("/api/person/v1/4")
                                && link.getType().equals("GET")
                ));

        assertNotNull(
                personFourth.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("findAll")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("GET")
                ));

        assertNotNull(
                personFourth.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("create")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("POST")
                ));

        assertNotNull(
                personFourth.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("update")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("PUT")
                ));

        assertNotNull(
                personFourth.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("delete")
                                && link.getHref().endsWith("/api/person/v1/4")
                                && link.getType().equals("DELETE")
                ));

        assertEquals("Address Test4", personFourth.getAddress());
        assertEquals("First Name Test4", personFourth.getFirstName());
        assertEquals("Last Name Test4", personFourth.getLastName());
        assertEquals("Male", personFourth.getGender());

        var personSeven = people.get(7);
        assertNotNull(personSeven);
        assertNotNull(personSeven.getId());
        assertNotNull(personSeven.getLinks());
        assertNotNull(
                personSeven.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("self")
                                && link.getHref().endsWith("/api/person/v1/7")
                                && link.getType().equals("GET")
                ));

        assertNotNull(
                personSeven.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("findAll")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("GET")
                ));

        assertNotNull(
                personSeven.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("create")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("POST")
                ));

        assertNotNull(
                personSeven.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("update")
                                && link.getHref().endsWith("/api/person/v1")
                                && link.getType().equals("PUT")
                ));

        assertNotNull(
                personSeven.getLinks().stream().anyMatch(
                        link -> link.getRel().equals("delete")
                                && link.getHref().endsWith("/api/person/v1/7")
                                && link.getType().equals("DELETE")
                ));

        assertEquals("Address Test7", personSeven.getAddress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());
    }
}