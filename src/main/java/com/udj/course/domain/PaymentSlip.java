package com.udj.course.domain;

import java.util.Date;

//boleto
public class PaymentSlip extends Payment {

    private Date startDate;
    private Date endDate;

    public PaymentSlip(){
        super();
    }

    public PaymentSlip(Integer id, Integer paymentState, Order order,
                       Date startDate, Date endDate) {
        super(id, paymentState, order);
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
