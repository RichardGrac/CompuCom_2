package com.example.CompuCom2.model.statistics;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AverageFrequentHourPurchases {

    private Integer hour;
    private Double average;

    @QueryProjection
    public AverageFrequentHourPurchases(Integer hour, Double average) {
        this.hour = hour;
        this.average = average;
    }
}
