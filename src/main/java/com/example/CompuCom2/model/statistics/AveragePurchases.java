package com.example.CompuCom2.model.statistics;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AveragePurchases {
    private Integer day;
    private Integer month;
    private Double average;

    @QueryProjection
    public AveragePurchases(Integer day, Integer month, Double average) {
        this.day = day;
        this.month = month;
        this.average = average;
    }
}
