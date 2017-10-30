package com.example.CompuCom2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "percentage")
    private Double percentage = 0.0;

    public Discount(){}

    public Discount(Integer id, Double percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", percentage=" + percentage +
                '}';
    }
}
