package com.example.CompuCom2.model;

public class ShoppingCartModel {

    private int idSc;
    private int idUser;
    private ProductModel product;
    private int quantity;

    public ShoppingCartModel(){}

    public ShoppingCartModel(int idSc, int idUser, ProductModel product, int quantity) {
        this.idSc = idSc;
        this.idUser = idUser;
        this.product = product;
        this.quantity = quantity;
    }

    public int getIdSc() {
        return idSc;
    }

    public void setIdSc(int idSc) {
        this.idSc = idSc;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartModel{" +
                "idSc=" + idSc +
                ", idUser=" + idUser +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
