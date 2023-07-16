package com.eduardonetto.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.entities.OrderProduct;
import com.eduardonetto.main.repositories.OrderProductRepository;

@Service
public class OrderProductService {

	@Autowired
	private OrderProductRepository repository;

	public List<OrderProduct> findAll() {
		return repository.findAll();
	}

	public OrderProduct insert(OrderProduct orderProduct) {
		return repository.save(orderProduct);
	}

	public void delete(OrderProduct orderProduct) {
		repository.delete(orderProduct);
	}

}
