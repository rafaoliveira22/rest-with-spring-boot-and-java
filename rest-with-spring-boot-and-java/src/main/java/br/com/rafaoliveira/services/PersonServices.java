package br.com.rafaoliveira.services;

import br.com.rafaoliveira.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private static final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    public List<Person> findByAll(){
        logger.info("Finding all persons");

        // NESSE PONTO QUE SE ACESSARIA A BASE DE DADOS
        // P/ RECUPERAR ESSA LISTAGEM

        List<Person> persons = new ArrayList<Person>();

        for(int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person findById(String id){
        logger.info("Finding a person");

        // NESSE PONTO QUE SE ACESSARIA A BASE DE DADOS
        // P/ RECUPERAR ESSE REGISTRO

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Rafael");
        person.setLastName("Souza");
        person.setAddress("Santana de Parnaíba - São Paulo - Brasil");
        person.setGender("Male");

        return person;
    }

    public Person create(Person person){
        logger.info("Creating a person");

        return person;
    }

    public Person update(Person person){
        logger.info("Updating a person");

        return person;
    }

    public void delete(String id){
        logger.info("Deleting a person");
    }

    private Person mockPerson(int i){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Person last name " + i);
        person.setAddress("Some address in Brasil " + i);
        person.setGender(i % 2 == 0 ? "Male" : "Female");

        return person;
    }

}
