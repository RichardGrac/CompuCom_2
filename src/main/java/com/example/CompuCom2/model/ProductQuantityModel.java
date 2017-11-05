package com.example.CompuCom2.model;

public class ProductQuantityModel {
    private Integer id;
    private Integer quantity;

    public ProductQuantityModel(){}

    public ProductQuantityModel(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductQuantityModel{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
