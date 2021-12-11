package com.udj.course.domain;

import com.udj.course.domain.enums.PaymentState;

public class Payment {

    private Integer id;
    private Integer paymentState;
    private Order order;

    public Payment() {
    }

    public Payment(Integer id, Integer paymentState, Order order) {
        this.id = id;
        this.paymentState = paymentState;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
