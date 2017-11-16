package com.example.CompuCom2.entity;

import javax.persistence.*;

@Entity
@Table(name = "Details")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "id_product")
    private int id_prod;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "quantity")
    private int quantity;

    public Details(){}

    public Details(int id_prod, String name, Double price, Double discount, int quantity) {
        this.id_prod = id_prod;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", id_prod=" + id_prod +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", quantity=" + quantity +
                '}';
    }
}
