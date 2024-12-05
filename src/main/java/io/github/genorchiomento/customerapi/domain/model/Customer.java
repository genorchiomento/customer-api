package io.github.genorchiomento.customerapi.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private String cpf;
    private String name;
    private String birthDate;
    private String phone;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(),
                customer.getId()) && Objects.equals(getCpf(),
                customer.getCpf()) && Objects.equals(getName(),
                customer.getName()) && Objects.equals(getBirthDate(),
                customer.getBirthDate()) && Objects.equals(getPhone(),
                customer.getPhone()) && Objects.equals(getAddress(),
                customer.getAddress()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCpf(), getName(), getBirthDate(), getPhone(), getAddress());
    }
}