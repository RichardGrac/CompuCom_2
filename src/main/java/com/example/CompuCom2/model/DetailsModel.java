package com.example.CompuCom2.model;

public class DetailsModel {

    private int id;
    private int id_prod;
    private String name;
    private Double price;
    private Double discount;
    private int quantity;

    public DetailsModel(){}

    public DetailsModel(int id, int id_prod, String name, Double price, Double discount, int quantity) {
        this.id = id;
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
        return "DetailsModel{" +
                "id=" + id +
                ", id_prod=" + id_prod +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", quantity=" + quantity +
                '}';
    }
}
