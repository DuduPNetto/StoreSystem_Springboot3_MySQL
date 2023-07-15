package com.eduardonetto.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardonetto.main.entities.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
