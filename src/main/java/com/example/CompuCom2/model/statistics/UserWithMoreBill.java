package com.example.CompuCom2.model.statistics;

import com.example.CompuCom2.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserWithMoreBill {

    private User user;
    private Long count;

    @QueryProjection
    public UserWithMoreBill(User user, Long count) {
        this.user = user;
        this.count = count;
    }
}
