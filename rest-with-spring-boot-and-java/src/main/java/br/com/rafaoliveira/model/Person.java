package br.com.rafaoliveira.model;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 6)
    private String gender;

    public Person(){}


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
        Person other = (Person) obj;

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
