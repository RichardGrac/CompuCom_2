package com.example.CompuCom2.model.statistics;

import com.example.CompuCom2.entity.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TopProduct {

    private Product product;
    private Integer sum;

    @QueryProjection
    public TopProduct(Product product, Integer sum) {
        this.product = product;
        this.sum = sum;
    }
}
