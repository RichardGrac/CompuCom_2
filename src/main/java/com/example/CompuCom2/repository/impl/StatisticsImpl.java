package com.example.CompuCom2.repository.impl;

import com.example.CompuCom2.converter.SalesProductViewConverter;
import com.example.CompuCom2.entity.*;
import com.example.CompuCom2.model.*;
import com.example.CompuCom2.model.statistics.*;
import com.example.CompuCom2.repository.SaleProductViewRepository;
import com.example.CompuCom2.repository.Statistics;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StatisticsImpl implements Statistics {

    @PersistenceContext
    private EntityManager entityManager;

    private final SaleProductViewRepository saleProductViewRepository;
    private final SalesProductViewConverter salesProductViewConverter;

    @Autowired
    StatisticsImpl(SaleProductViewRepository saleProductViewRepository,
                     SalesProductViewConverter salesProductViewConverter){
        this.saleProductViewRepository = saleProductViewRepository;
        this.salesProductViewConverter = salesProductViewConverter;
    }



    @Override
    public String monthWithMorePurchases() throws DataAccessException{
        QBill bill = QBill.bill;
        Integer month = new JPAQuery<>(entityManager)
                .from(bill)
                .select(bill.date.month())
                .groupBy(bill.date.month())
                .orderBy(bill.id.count().desc())
                .limit(1)
                .fetchOne();
        LocalDate localDate = LocalDate.of(1, month,1);
        return localDate.getMonth().name();
    }

    @Override
    public UserWithMoreBill customerWithMorePurchases() throws DataAccessException {
        QUser user = QUser.user;
        QBill bill = QBill.bill;
        return new JPAQuery<Tuple>(entityManager)
                .from(user, bill)
                .select(Projections.constructor(UserWithMoreBill.class, user, bill.bill_id.count()))
                .where(user.id.eq(bill.bill_id))
                .groupBy(bill.bill_id)
                .orderBy(bill.bill_id.count().desc()).limit(1).fetchOne();
    }

    @Override
    public List<Product> productsWithLittleAvailability(){
        QProduct product = QProduct.product;
        QProductQuantity productQuantity = QProductQuantity.productQuantity;
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);
        return query
                .from(productQuantity, product)
                .select(product)
                .where(product.id.eq(productQuantity.id)
                        .and(productQuantity.quantity.lt(5)))
                .fetch();
    }

    @Override
    public List<AveragePurchases> averagePurchasesPerDay(){ ///falta
        QBill bill = QBill.bill;
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);
        return query
                .from(bill)
                .select(Projections.constructor(AveragePurchases.class,bill.date.dayOfMonth(), bill.date.month(), bill.total.avg()))
                .groupBy(bill.date)
                .fetch();
    }

    @Override
    public Double averagePurchasesPerCurrentDay(){
        QBill bill = QBill.bill;
        return new JPAQuery<>(entityManager)
                .from(bill)
                .select(bill.total.avg())
                .where(bill.date.dayOfMonth().eq(Expressions.currentDate().dayOfMonth()))
                .fetchOne();
    }

    @Override
    public List<NumberSalesDay> numberOfSalesPerDay() { ///falta
        QBill bill = QBill.bill;
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);
        return query
                .from(bill)
                .select(Projections.constructor(NumberSalesDay.class,bill.date.dayOfMonth(), bill.date.month(), bill.total.count()))
                .groupBy(bill.date.dayOfMonth())
                .fetch();
    }

    @Override
    public List<AverageFrequentHourPurchases> averageOfMostFrequentHourOfPurchases() {
        QBill bill = QBill.bill;
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);
        return query
                .from(bill)
                .select(Projections.constructor(AverageFrequentHourPurchases.class, bill.date.hour(), bill.total.avg()))
                .groupBy(bill.date.hour())
                .fetch();
    }

    @Override
    public List<EarningsPerMonth> sumOfEarningsPerMonth() {
        QBill bill = QBill.bill;
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);
        return query
                .from(bill)
                .select(Projections.constructor(EarningsPerMonth.class, bill.date.month(), bill.total.sum()))
                .groupBy(bill.date.month())
                .fetch();
    }

    @Override
    public List<SalesProductViewModel> salesPerProductInThCurrentMonth() {
        List<SalesProductViewModel> list = new ArrayList<>();
        for (SalesProductView salesProductView : saleProductViewRepository.findAll()){
            list.add(salesProductViewConverter.entityToModel(salesProductView));
        }
        return list;
    }

    @Override
    public List<TopProduct> topProductHistory() {
        QDetails details = QDetails.details;
        QProduct product = QProduct.product;
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);
        return query
                .from(details, product)
                .select(Projections.constructor(TopProduct.class, product, details.quantity.sum()))
                .groupBy(details.id_prod)
                .orderBy(details.quantity.sum().desc())
                .limit(10)
                .where(details.id_prod.eq(product.id))
                .fetch();
    }

    @Override
    public Long billsPerCurrentDay() {
        QBill bill = QBill.bill;
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);
        return query
                .from(bill)
                .select(bill.id.count())
                .where(bill.date.dayOfMonth().eq(Expressions.currentDate().dayOfMonth())).fetchOne();
    }

    @Override
    public InverntoryStatistics percentageInventory() {
        QDetails details = QDetails.details;
        QProductQuantity productQuantity = QProductQuantity.productQuantity;
        Integer sumaTotalActualInventario = new JPAQuery<>(entityManager)
                .from(productQuantity)
                .select(productQuantity.quantity.sum()).fetchOne();
        Integer sumaProductoVendido = new JPAQuery<>(entityManager)
                .from(details)
                .select(details.quantity.sum()).fetchOne();
        Integer percentage = (100 * sumaProductoVendido) / (sumaProductoVendido + sumaTotalActualInventario);
        return InverntoryStatistics.builder()
                .inventory((sumaProductoVendido + sumaTotalActualInventario))
                .percentage(percentage)
                .build();
    }
}
