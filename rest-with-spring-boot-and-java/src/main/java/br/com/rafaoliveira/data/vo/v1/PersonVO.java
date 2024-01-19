package br.com.rafaoliveira.data.vo.v1;

import jakarta.persistence.*;

import java.io.Serializable;


public class PersonVO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonVO(){}

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        // Se as duas referências apontam para o mesmo objeto na memória
        if (this == obj)
            return true;

        // Se o objeto passado como argumento é nulo.
        // Se for, os objetos não podem ser iguais
        if (obj == null)
            return false;

        // Se os objetos pertencem à mesma classe
        if (getClass() != obj.getClass())
            return false;

        // Converte o objeto passado (obj) para a classe Person
        // para que os campos possam ser comparados.
        PersonVO other = (PersonVO) obj;

        // Comparação de cada campo individualmente e retorna false
        // Se algum deles for diferente.
        if (getAddress() == null) {
            if (other.getAddress() != null)
                return false;
        } else if (!getAddress().equals(other.getAddress()))
            return false;
        if (getFirstName() == null) {
            if (other.getFirstName() != null)
                return false;
        } else if (!getFirstName().equals(other.getFirstName()))
            return false;
        if (getGender() == null) {
            if (other.getGender() != null)
                return false;
        } else if (!getGender().equals(other.getGender()))
            return false;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (getLastName() == null) {
            if (other.getLastName() != null)
                return false;
        } else if (!getLastName().equals(other.getLastName()))
            return false;
        return true;
    }
}
