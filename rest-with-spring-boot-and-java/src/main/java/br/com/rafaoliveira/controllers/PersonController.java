package br.com.rafaoliveira.controllers;

import br.com.rafaoliveira.data.vo.v1.PersonVO;
import br.com.rafaoliveira.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.rafaoliveira.util.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/v1")
public class PersonController {
    @Autowired
    private PersonServices service;

    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<PersonVO> findByAll()  {
        return service.findByAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public PersonVO findById(@PathVariable Long id)  {
        return service.findById(id);
    }

    @PostMapping(
            value = "/",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public PersonVO create(@RequestBody PersonVO person)  {
        return service.create(person);
    }

    @PutMapping(
            value = "/",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public PersonVO update(@RequestBody PersonVO person)  {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)  {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
