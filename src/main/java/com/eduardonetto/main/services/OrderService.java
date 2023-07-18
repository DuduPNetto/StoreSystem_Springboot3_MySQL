package com.eduardonetto.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.controllers.dto.OrderDTO;
import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.repositories.OrderRepository;
import com.eduardonetto.main.services.exceptions.DatabaseException;
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
		try {
			repository.delete(order);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Order update(Long id, Order order) {
		Order entity = findById(id);
		updateOrder(entity, order);
		return repository.save(entity);
	}

	private void updateOrder(Order entity, Order order) {
		entity.setClient(order.getClient());
		entity.setOrderStatus(order.getOrderStatus());
	}

	public Order fromDto(OrderDTO orderDto) {
		Order order = new Order();
		order.setClient(orderDto.getClient());
		order.setOrderStatus(orderDto.getOrderStatus());
		return order;
	}

}
