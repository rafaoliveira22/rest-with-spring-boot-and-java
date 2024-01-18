package br.com.rafaoliveira.services;

import br.com.rafaoliveira.exceptions.ResourceNotFoundException;
import br.com.rafaoliveira.model.Person;
import br.com.rafaoliveira.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    public PersonRepository repository;

    public List<Person> findByAll(){
        logger.info("Finding all persons");

        return repository.findAll();
    }

    public Person findById(Long id){
        logger.info("Finding a person");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
    }

    public Person create(Person person){
        logger.info("Creating a person");

        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating a person");
        Person entity = repository.findById(person.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting a person");
        Person person = repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(person);
    }

}
