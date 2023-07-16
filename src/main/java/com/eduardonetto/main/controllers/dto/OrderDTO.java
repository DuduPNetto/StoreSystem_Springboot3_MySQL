package com.eduardonetto.main.controllers.dto;

import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.entities.User;

public class OrderDTO {

	private Long id;
	private User client;

	public OrderDTO() {
	}

	public OrderDTO(Order order) {
		this.id = order.getId();
		this.client = order.getClient();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

}
