package com.example.CompuCom2.model;

public class ProductCategoryModel {

    private Integer id;
    private String name;

    public ProductCategoryModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductCategoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
