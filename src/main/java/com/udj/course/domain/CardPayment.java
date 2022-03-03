package com.udj.course.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.udj.course.domain.enums.PaymentState;

import javax.persistence.Entity;

@Entity
@JsonTypeName("CardPayment")
public class CardPayment extends Payment{

    private Integer installments;

    public CardPayment(){
        super();
    }

    public CardPayment(PaymentState paymentState, ProductOrder productOrder, Integer installments) {
        super(paymentState, productOrder);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
