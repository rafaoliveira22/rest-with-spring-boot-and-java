package br.com.rafaoliveira.mapper.custom;

import br.com.rafaoliveira.data.vo.v2.PersonVOV2;
import br.com.rafaoliveira.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonVOV2 convertEntityToVo(Person person){
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthday(new Date());
        vo.setGender(person.getGender());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());

        return vo;
    }

    public Person convertVoToEntity(PersonVOV2 vo){
        Person person = new Person();

        person.setId(vo.getId());
        person.setAddress(vo.getAddress());
        person.setGender(vo.getGender());
        person.setFirstName(vo.getFirstName());
        person.setLastName(vo.getLastName());

        return person;
    }
}
