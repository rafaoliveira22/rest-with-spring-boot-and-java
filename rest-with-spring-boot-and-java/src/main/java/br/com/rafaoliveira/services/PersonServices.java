package br.com.rafaoliveira.services;

import br.com.rafaoliveira.data.vo.v1.PersonVO;
import br.com.rafaoliveira.exceptions.ResourceNotFoundException;
import br.com.rafaoliveira.mapper.DozerMapper;
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

    public List<PersonVO> findByAll(){
        logger.info("Finding all persons");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id){
        logger.info("Finding a person");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        return DozerMapper.parseObject(person, PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        logger.info("Creating a person");

        Person personEntity = DozerMapper.parseObject(person, Person.class);
        PersonVO personVO = DozerMapper.parseObject(repository.save(personEntity), PersonVO.class);

        return personVO;
    }

    public PersonVO update(PersonVO person){
        logger.info("Updating a person");
        Person personEntity = repository.findById(person.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setAddress(person.getAddress());
        personEntity.setGender(person.getGender());

        PersonVO personVO = DozerMapper.parseObject(repository.save(personEntity), PersonVO.class);
        return personVO;
    }

    public void delete(Long id){
        logger.info("Deleting a person");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(person);
    }

}
