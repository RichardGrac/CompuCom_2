package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.Product;
import com.example.CompuCom2.model.*;
import com.example.CompuCom2.model.statistics.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface Statistics {

    String monthWithMorePurchases() throws DataAccessException;
    Long billsPerCurrentDay();
    UserWithMoreBill customerWithMorePurchases() throws DataAccessException;
    List<Product> productsWithLittleAvailability();
    List<AveragePurchases> averagePurchasesPerDay();
    List<NumberSalesDay> numberOfSalesPerDay();
    List<AverageFrequentHourPurchases> averageOfMostFrequentHourOfPurchases();
    List<EarningsPerMonth> sumOfEarningsPerMonth();
    List<SalesProductViewModel> salesPerProductInThCurrentMonth();
    List<TopProduct> topProductHistory();
    InverntoryStatistics percentageInventory();
    Double averagePurchasesPerCurrentDay();

}
