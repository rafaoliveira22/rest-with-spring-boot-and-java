package br.com.rafaoliveira.services;

import br.com.rafaoliveira.controllers.PersonController;
import br.com.rafaoliveira.data.dto.v1.PersonDTOV1;
import br.com.rafaoliveira.data.dto.v2.PersonDTOV2;
import br.com.rafaoliveira.exceptions.RequiredObjectIsNullException;
import br.com.rafaoliveira.exceptions.ResourceNotFoundException;
import br.com.rafaoliveira.mapper.ObjectMapper;
import br.com.rafaoliveira.mapper.custom.PersonMapper;
import br.com.rafaoliveira.model.Person;
import br.com.rafaoliveira.repositories.PersonRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class PersonServices {
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    public PersonRepository repository;

    @Autowired
    public PersonMapper personMapper;

    public List<PersonDTOV1> findByAll(){
        logger.info("Finding all persons");

        var persons = ObjectMapper.parseListObjects(repository.findAll(), PersonDTOV1.class);
        persons.stream().forEach(person -> person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel()));

        return persons;
    }

    public PersonDTOV1 findById(Long id){
        logger.info("Finding a person");
        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        PersonDTOV1 dto =  ObjectMapper.parseObject(person, PersonDTOV1.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return dto;
    }

    public PersonDTOV1 create(PersonDTOV1 person){
        if(person == null){
            throw new RequiredObjectIsNullException();
        }
        logger.info("Creating a person");

        Person personEntity = ObjectMapper.parseObject(person, Person.class);
        PersonDTOV1 dto = ObjectMapper.parseObject(repository.save(personEntity), PersonDTOV1.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId()))).withSelfRel());

        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){
        if(person == null){
            throw new RequiredObjectIsNullException();
        }
        logger.info("Creating a Person V2");

        Person personEntity = personMapper.convertDTOToEntity(person);
        PersonDTOV2 dto = personMapper.convertEntityToDTO(repository.save(personEntity));
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId()))).withSelfRel());

        return dto;
    }

    public PersonDTOV1 update(PersonDTOV1 person){
        if(person == null){
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating a person");
        Person personEntity = repository.findById(person.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setAddress(person.getAddress());
        personEntity.setGender(person.getGender());

        PersonDTOV1 dto = ObjectMapper.parseObject(repository.save(personEntity), PersonDTOV1.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel());

        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting a person");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(person);
    }

}
