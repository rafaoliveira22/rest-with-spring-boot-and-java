package br.com.rafaoliveira.data.dto.v1;


import br.com.rafaoliveira.serializer.GenderSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//@JsonPropertyOrder({"id", "address", "firstName", "last_name", "gender"})
@JsonFilter("PersonFilter")
public class PersonDTOV1 extends RepresentationModel<PersonDTOV1>  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;

    //@JsonProperty("last_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    private String address;

    //@JsonIgnore
    @JsonSerialize(using = GenderSerializer.class)
    private String gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDay;

    public PersonDTOV1(){}


    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonDTOV1 that = (PersonDTOV1) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(gender, that.gender) && Objects.equals(birthDay, that.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, address, gender, birthDay);
    }
}
