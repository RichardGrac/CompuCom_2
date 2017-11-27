package com.example.CompuCom2.model.statistics;

import com.example.CompuCom2.entity.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductLowAvailability {

    private Product product;
    private Integer quantity;

    @QueryProjection

    public ProductLowAvailability(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
