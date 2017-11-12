package com.example.CompuCom2.entity;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sc")
    private int idSc;

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "id_prod")
    private int idProd;

    @Column(name = "quantity")
    private int quantity;

    public  ShoppingCart(){}

    public ShoppingCart(int idUser, int idProd, int quantity) {
        this.idUser = idUser;
        this.idProd = idProd;
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

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "idSc=" + idSc +
                ", idUser=" + idUser +
                ", idProd=" + idProd +
                ", quantity=" + quantity +
                '}';
    }
}

