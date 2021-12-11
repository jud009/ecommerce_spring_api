package com.udj.course.domain;

public class CardPayment extends Payment{

    private Integer installments;

    public CardPayment(){
        super();
    }

    public CardPayment(Integer id, Integer paymentState, Order order, Integer installments) {
        super(id, paymentState, order);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
