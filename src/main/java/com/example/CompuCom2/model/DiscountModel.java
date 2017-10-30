package com.example.CompuCom2.model;

public class DiscountModel {
    private Integer id;
    private Double percentage = 0.0;

    public DiscountModel(){
    }

    public DiscountModel(Integer id, Double percentage) {
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
        return "DiscountModel{" +
                "id=" + id +
                ", percentage=" + percentage +
                '}';
    }
}
