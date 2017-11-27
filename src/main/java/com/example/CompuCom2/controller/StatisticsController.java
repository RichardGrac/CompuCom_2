package com.example.CompuCom2.controller;

import com.example.CompuCom2.listener.LoginListener;
import com.example.CompuCom2.listener.SessionCounterListener;
import com.example.CompuCom2.model.statistics.EarningsPerMonth;
import com.example.CompuCom2.model.statistics.InverntoryStatistics;
import com.example.CompuCom2.model.statistics.UserWithMoreBill;
import com.example.CompuCom2.repository.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final Statistics statistics;

    @Autowired
    StatisticsController(Statistics statistics){
        this.statistics = statistics;
    }

    @GetMapping("/bill-day")
    @ResponseBody
    public Long billPerCurrentDay(){
        return statistics.billsPerCurrentDay();
    }

    @GetMapping("/users-active")
    @ResponseBody
    public int usersActive(){
        return SessionCounterListener.getTotalActiveSession();
    }

    @GetMapping("/user-logged")
    @ResponseBody
    public int userLogged(){
        return LoginListener.getCouter();
    }

    @GetMapping("/earnigs-months")
    @ResponseBody
    public List<EarningsPerMonth> earnigsMonths(){
        return statistics.sumOfEarningsPerMonth();
    }

    @GetMapping("/user-max")
    @ResponseBody
    public UserWithMoreBill userMax(){
        return statistics.customerWithMorePurchases();
    }

    @GetMapping("/month-max")
    @ResponseBody
    public String monthMax(){
        return statistics.monthWithMorePurchases();
    }

    @GetMapping("/percentage-inventory")
    @ResponseBody
    public InverntoryStatistics percentageInventory(){
        return statistics.percentageInventory();
    }

    @GetMapping("/avg-sales-day")
    @ResponseBody
    public Double avgSalesDay(){
        return statistics.averagePurchasesPerCurrentDay();
    }
}
