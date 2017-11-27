package com.example.CompuCom2.model.statistics;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EarningsPerMonth {
    private Integer month;
    private Double sum;

    @QueryProjection
    public EarningsPerMonth(Integer month, Double sum) {
        this.month = month;
        this.sum = sum;
    }
}
