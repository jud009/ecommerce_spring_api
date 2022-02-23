package com.udj.course.dto;

import com.udj.course.services.validation.ClientInsert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.udj.course.Constants.CANT_BE_EMPTY;
import static com.udj.course.Constants.INVALID_EMAIL;

@ClientInsert
public class ClientNewDTO implements Serializable {

    @NotEmpty(message = CANT_BE_EMPTY)
    @Size(min = 4, max = 50, message = "Length should be between 4 and 50 chars")
    private String name;

    @NotEmpty(message = CANT_BE_EMPTY)
    @Email(message = INVALID_EMAIL)
    private String email;

    @NotEmpty(message = CANT_BE_EMPTY)

    private String cpfOrCnpj;

    private Integer type;

    @NotEmpty(message = CANT_BE_EMPTY)
    private String publicPlace;

    @NotEmpty(message = CANT_BE_EMPTY)
    private String number;

    private String addressReference;
    private String neighborhood;

    @NotEmpty(message = CANT_BE_EMPTY)
    private String zipCode;

    @NotEmpty(message = CANT_BE_EMPTY)
    private String phone1;

    private String phone2;
    private String phone3;

    private Integer cityId;

    public ClientNewDTO() {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddressReference() {
        return addressReference;
    }

    public void setAddressReference(String addressReference) {
        this.addressReference = addressReference;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
