package com.example.CompuCom2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Bill")
@Builder
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "shipping_prince")
    private Double shipping_price;

    @Column(name = "iva")
    private Double iva;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "total")
    private Double total;

    @Column(name = "date")
    LocalDateTime date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bill_details", joinColumns =
        @JoinColumn(name = "bill_id", referencedColumnName = "id"),
        inverseJoinColumns =
        @JoinColumn(name = "details_id", referencedColumnName = "id"))
    private List<Details> details_bill;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id")
    private Shipping shipping;

    public Bill(){}

    public Bill(Double shipping_price, Double iva, Double subtotal, Double total, LocalDateTime date, List<Details> details_bill, Shipping shipping) {

        this.shipping_price = shipping_price;
        this.iva = iva;
        this.subtotal = subtotal;
        this.total = total;
        this.date = date;
        this.details_bill = details_bill;
        this.shipping = shipping;
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

    public List<Details> getDetails_bill() {
        return details_bill;
    }

    public void setDetails_bill(List<Details> details_bill) {
        this.details_bill = details_bill;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", shipping_price=" + shipping_price +
                ", iva=" + iva +
                ", subtotal=" + subtotal +
                ", total=" + total +
                ", date=" + date +
                ", details_bill=" + details_bill +
                ", shipping=" + shipping +
                '}';
    }
}
