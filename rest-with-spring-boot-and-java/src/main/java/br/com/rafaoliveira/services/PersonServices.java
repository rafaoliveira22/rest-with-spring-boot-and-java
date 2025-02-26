package br.com.rafaoliveira.services;

import br.com.rafaoliveira.controllers.PersonController;
import br.com.rafaoliveira.data.dto.PersonDTO;
import br.com.rafaoliveira.exceptions.RequiredObjectIsNullException;
import br.com.rafaoliveira.exceptions.ResourceNotFoundException;
import br.com.rafaoliveira.mapper.ObjectMapper;
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

    public List<PersonDTO> findByAll(){
        logger.info("Finding all persons");

        var persons = ObjectMapper.parseListObjects(repository.findAll(), PersonDTO.class);
        persons.stream().forEach(person -> person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel()));

        return persons;
    }

    public PersonDTO findById(Long id){
        logger.info("Finding a person");
        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        PersonDTO dto =  ObjectMapper.parseObject(person, PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return dto;
    }

    public PersonDTO create(PersonDTO person){
        if(person == null){
            throw new RequiredObjectIsNullException();
        }
        logger.info("Creating a person");

        Person personEntity = ObjectMapper.parseObject(person, Person.class);
        PersonDTO dto = ObjectMapper.parseObject(repository.save(personEntity), PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId()))).withSelfRel());

        return dto;
    }

    public PersonDTO update(PersonDTO person){
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

        PersonDTO dto = ObjectMapper.parseObject(repository.save(personEntity), PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel());

        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting a person");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(person);
    }

}
