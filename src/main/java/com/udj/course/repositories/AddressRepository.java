package com.udj.course.repositories;

import com.udj.course.domain.Address;
import com.udj.course.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
