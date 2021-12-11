package com.udj.course.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private Integer id;
    private Date instant;
    private Client client;
    private Payment payment;

    private List<Product> products = new ArrayList<>();

    public Order() {
    }

    public Order(Integer id, Date instant, Client client, Payment payment) {
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.payment = payment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
