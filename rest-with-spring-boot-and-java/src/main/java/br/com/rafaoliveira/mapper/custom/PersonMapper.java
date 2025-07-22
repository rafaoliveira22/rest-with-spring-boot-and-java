package br.com.rafaoliveira.mapper.custom;

import br.com.rafaoliveira.data.dto.v1.PersonDTOV1;
import br.com.rafaoliveira.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonDTOV1 convertEntityToDTO(Person person){
        PersonDTOV1 dto = new PersonDTOV1();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());

        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV1 dto){
        Person entity = new Person();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        return entity;
    }
}
