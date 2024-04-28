package br.com.rafaoliveira.services;

import br.com.rafaoliveira.controllers.PersonController;
import br.com.rafaoliveira.data.vo.v1.PersonVO;
import br.com.rafaoliveira.exceptions.RequiredObjectIsNullException;
import br.com.rafaoliveira.exceptions.ResourceNotFoundException;
import br.com.rafaoliveira.mapper.DozerMapper;
import br.com.rafaoliveira.model.Person;
import br.com.rafaoliveira.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    public PersonRepository repository;

    public List<PersonVO> findByAll(){
        logger.info("Finding all persons");

        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream().forEach(person -> person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel()));

        return persons;
    }

    public PersonVO findById(Long id){
        logger.info("Finding a person");
        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        PersonVO vo =  DozerMapper.parseObject(person, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public PersonVO create(PersonVO person){
        if(person == null){
            throw new RequiredObjectIsNullException();
        }
        logger.info("Creating a person");

        Person personEntity = DozerMapper.parseObject(person, Person.class);
        PersonVO vo = DozerMapper.parseObject(repository.save(personEntity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public PersonVO update(PersonVO person){
        if(person == null){
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating a person");
        Person personEntity = repository.findById(person.getKey())
                        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setAddress(person.getAddress());
        personEntity.setGender(person.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(personEntity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(Long id){
        logger.info("Deleting a person");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(person);
    }

}
