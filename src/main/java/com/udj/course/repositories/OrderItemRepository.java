package com.udj.course.repositories;

import com.udj.course.domain.Address;
import com.udj.course.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
