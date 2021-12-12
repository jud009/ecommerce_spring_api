package com.udj.course.services;

import com.udj.course.domain.ProductOrder;
import com.udj.course.repositories.OrderRepository;
import com.udj.course.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private static final String OBJECT_NOT_FOUND = OrderService.class.getSimpleName()+ ": OBJETO NÃO ENCONTRADO";

    @Autowired
    private OrderRepository repository;

    public ProductOrder findById(Integer id) {
        Optional<ProductOrder> op = repository.findById(id);
        return op.orElseThrow(()-> new ObjectNotFoundException(OBJECT_NOT_FOUND));
    }
}
