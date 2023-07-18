package com.eduardonetto.main.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardonetto.main.controllers.dto.OrderDTO;
import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.services.OrderService;
import com.eduardonetto.main.services.UserService;
import com.eduardonetto.main.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/backend/dev/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
		List<OrderDTO> list = orderService.findAll().stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
		Order order = orderService.findById(id);
		return ResponseEntity.ok().body(new OrderDTO(order));
	}

	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO orderDto) {
		Order order = orderService.fromDto(orderDto);
		User user = userService.findByNameAndEmail(order.getClient().getName(), order.getClient().getEmail());
		if (user != null) {
			order.setClient(user);
			order = orderService.insert(order);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId())
					.toUri();
			return ResponseEntity.created(uri).body(new OrderDTO(order));
		} else {
			throw new ObjectNotFoundException("Object not found with [name=" + order.getClient().getName() + ", email="
					+ order.getClient().getEmail());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		orderService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody Order order) {
		User entity = userService.findByNameAndEmail(order.getClient().getName(), order.getClient().getEmail());
		order.setClient(entity);
		if (entity != null) {
			Order obj = orderService.update(id, order);
			return ResponseEntity.ok().body(new OrderDTO(obj));
		} else {
			throw new ObjectNotFoundException("Object not found with [name=" + order.getClient().getName() + ", email="
					+ order.getClient().getEmail());
		}
	}

}
