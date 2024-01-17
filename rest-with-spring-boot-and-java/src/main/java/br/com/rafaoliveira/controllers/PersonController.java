package br.com.rafaoliveira.controllers;

import br.com.rafaoliveira.model.Person;
import br.com.rafaoliveira.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/person")
public class PersonController {

    // Instanciação manual s/ injeção de depências[
    // private PersonServices service = new PersonServices();

    // Injeção de depêndencias automática (@Service - @Autowired)
    @Autowired
    private PersonServices service;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findByAll()  {
        return service.findByAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable String id)  {
        return service.findById(id);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person)  {
        return service.create(person);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person)  {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id)  {
        service.delete(id);
    }
}
