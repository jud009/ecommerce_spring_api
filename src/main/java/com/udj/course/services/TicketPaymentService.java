package com.udj.course.services;

import com.udj.course.domain.PaymentTicket;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TicketPaymentService {


    public void fillPayment(PaymentTicket payment, Date orderInstant){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderInstant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setEndDate(calendar.getTime());
    }
}
