package com.eduardonetto.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardonetto.main.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
