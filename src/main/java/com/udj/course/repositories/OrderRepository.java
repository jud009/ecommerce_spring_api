package com.udj.course.repositories;

import com.udj.course.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder, Integer> {
}
