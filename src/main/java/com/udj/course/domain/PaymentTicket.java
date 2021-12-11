package com.udj.course.domain;

import com.udj.course.domain.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentTicket extends Payment {

    private Date startDate;
    private Date endDate;

    public PaymentTicket(){
        super();
    }

    public PaymentTicket(PaymentState paymentState, ProductOrder productOrder,
                         Date startDate, Date endDate) {
        super(paymentState, productOrder);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
