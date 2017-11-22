package com.example.CompuCom2.entity;

import javax.persistence.*;

@Entity
@Table(name = "Shipping")
public class Shipping {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "colony")
    private String colony;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "country")
    private String country;

    @Column(name = "reference")
    private String reference;

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", colony='" + colony + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", reference='" + reference + '\'' +
                ", status_shipping=" + status_shipping +
                '}';
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_shipping_id")
    private StatusShipping status_shipping = null;

    public Shipping(){}

    public Shipping(String street, String number, String colony, String city, String state, String zip, String country, String reference, StatusShipping status_shipping) {
        this.street = street;
        this.number = number;
        this.colony = colony;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.reference = reference;
        this.status_shipping = status_shipping;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public StatusShipping getStatus_shipping() {
        return status_shipping;
    }

    public void setStatus_shipping(StatusShipping status_shipping) {
        this.status_shipping = status_shipping;
    }

}
