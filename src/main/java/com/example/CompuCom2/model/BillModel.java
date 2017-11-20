package com.example.CompuCom2.model;

import java.time.LocalDateTime;
import java.util.List;

public class BillModel {
    private int id;
    private Double shipping_price;
    private Double iva;
    private Double subtotal;
    private Double total;
    private LocalDateTime date;
    private List<DetailsModel> details_bill;
    private ShippingModel shipping;
    private int id_user;

    public BillModel(){}

    public BillModel(int id, Double shipping_price, Double iva, Double subtotal, Double total, LocalDateTime date, List<DetailsModel> details_bill, ShippingModel shipping) {
        this.id = id;
        this.shipping_price = shipping_price;
        this.iva = iva;
        this.subtotal = subtotal;
        this.total = total;
        this.date = date;
        this.details_bill = details_bill;
        this.shipping = shipping;;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(Double shipping_price) {
        this.shipping_price = shipping_price;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<DetailsModel> getDetails_bill() {
        return details_bill;
    }

    public void setDetails_bill(List<DetailsModel> details_bill) {
        this.details_bill = details_bill;
    }

    public ShippingModel getShipping() {
        return shipping;
    }

    public void setShipping(ShippingModel shipping) {
        this.shipping = shipping;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "BillModel{" +
                "id=" + id +
                ", shipping_price=" + shipping_price +
                ", iva=" + iva +
                ", subtotal=" + subtotal +
                ", total=" + total +
                ", date=" + date +
                ", details_bill=" + details_bill +
                ", shipping=" + shipping +
                ", id_user=" + id_user +
                '}';
    }
}
