package com.udj.course.dto;

import com.udj.course.Constants;
import com.udj.course.domain.Client;
import com.udj.course.services.validation.ClientUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.udj.course.Constants.CANT_BE_EMPTY;
import static com.udj.course.Constants.INVALID_EMAIL;

@ClientUpdate
public class ClientDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = CANT_BE_EMPTY)
    @Size(min = 4, max = 50, message = "Length should be between 4 and 50 chars")
    private String name;

    @NotEmpty(message = CANT_BE_EMPTY)
    @Email(message = INVALID_EMAIL)
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
