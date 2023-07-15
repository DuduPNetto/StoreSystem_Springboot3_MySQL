package com.eduardonetto.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.repositories.OrderRepository;
import com.eduardonetto.main.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> order = repository.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id = " + id));
	}

	public Order insert(Order order) {
		return repository.save(order);
	}

	public void delete(Long id) {
		Order order = findById(id);
		repository.delete(order);
	}

	public Order update(Long id, Order order) {
		Order entity = findById(id);
		updateOrder(entity, order);
		return repository.save(entity);
	}

	private void updateOrder(Order entity, Order order) {
		entity.setClient(order.getClient());
	}

}
