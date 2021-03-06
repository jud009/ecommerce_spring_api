package com.udj.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udj.course.domain.enums.ClientType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    //@Column(unique = true) //don't allow the same email
    private String email;

    private String cpfOrCnpj;
    private Integer type;

    // cascade = CascadeType.ALL = delete all when delete client
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "phone_number")
    private Set<String> phoneNumbers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<ProductOrder> productOrders = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String email, String cpfOrCnpj, ClientType type) {
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (type == null) ? null : type.getId();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public ClientType getType() {
        return ClientType.getById(type);
    }

    public void setType(ClientType type) {
        this.type = type.getId();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<ProductOrder> getOrders() {
        return productOrders;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
