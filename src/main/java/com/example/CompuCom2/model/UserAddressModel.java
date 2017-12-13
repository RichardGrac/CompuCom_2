package com.example.CompuCom2.model;

import lombok.Builder;

@Builder
public class UserAddressModel {
    private int id;
    private String street;
    private String number;
    private String colony;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String reference;

    public UserAddressModel(){}

    public UserAddressModel(int id, String street, String number, String colony, String city, String state, String zip, String country, String reference) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.colony = colony;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "UserAddressModel{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", colony='" + colony + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
