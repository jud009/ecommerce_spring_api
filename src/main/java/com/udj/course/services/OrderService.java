package com.udj.course.services;

import com.udj.course.domain.OrderItem;
import com.udj.course.domain.PaymentTicket;
import com.udj.course.domain.ProductOrder;
import com.udj.course.domain.enums.PaymentState;
import com.udj.course.repositories.OrderItemRepository;
import com.udj.course.repositories.OrderRepository;
import com.udj.course.repositories.PaymentRepository;
import com.udj.course.repositories.ProductRepository;
import com.udj.course.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    private static final String OBJECT_NOT_FOUND = OrderService.class.getSimpleName()+ ": OBJETO NÃO ENCONTRADO";

    @Autowired
    private OrderRepository repository;

    @Autowired
    private TicketPaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public ProductOrder findById(Integer id) {
        Optional<ProductOrder> op = repository.findById(id);
        return op.orElseThrow(()-> new ObjectNotFoundException(OBJECT_NOT_FOUND));
    }

    public ProductOrder insert(ProductOrder order){
        order.setId(null);
        order.setInstant(new Date());
        order.getPayment().setPaymentState(PaymentState.PENDING);
        order.getPayment().setOrder(order);
        if(order.getPayment() instanceof PaymentTicket){
            PaymentTicket paymentTicket = (PaymentTicket) order.getPayment();
            paymentService.fillPayment(paymentTicket, order.getInstant());

        }
        order = repository.save(order);
        paymentRepository.save(order.getPayment());

        for (OrderItem item: order.getItems()){
            item.setDiscount(0.0);
            item.setOrder(order);
        }

        orderItemRepository.saveAll(order.getItems());

        return order;
    }
}
