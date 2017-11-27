package com.example.CompuCom2.model.statistics;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NumberSalesDay {

    private Integer day;
    private Integer month;
    private Long count;

    @QueryProjection
    public NumberSalesDay(Integer day, Integer month, Long count) {
        this.day = day;
        this.month = month;
        this.count = count;
    }
}
