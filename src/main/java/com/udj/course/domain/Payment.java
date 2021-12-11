package com.udj.course.domain;

import com.udj.course.domain.enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //tabelão
public abstract class Payment implements Serializable {
    @Id
    private Integer id;

    private Integer paymentState;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId //order id the same payment id
    private ProductOrder productOrder;

    public Payment() {
    }

    public Payment(PaymentState paymentState, ProductOrder productOrder) {
        this.paymentState = paymentState.getId();
        this.productOrder = productOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getPaymentState() {
        return PaymentState.getById(paymentState);
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState.getId();
    }

    public ProductOrder getOrder() {
        return productOrder;
    }

    public void setOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
