package com.example.CompuCom2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_quantity")
public class ProductQuantity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    public ProductQuantity(){}

    public ProductQuantity(Integer id, Integer quantity) {
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
        return "ProductQuantity{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
