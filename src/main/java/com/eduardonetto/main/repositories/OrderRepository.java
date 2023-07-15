package com.eduardonetto.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardonetto.main.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
