package com.example.CompuCom2.model.statistics;

import com.example.CompuCom2.entity.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SalesProductCurrentMonth {
    private Product product;
    private Long count;

    @QueryProjection
    public SalesProductCurrentMonth(Product product, Long count) {
        this.product = product;
        this.count = count;
    }
}
