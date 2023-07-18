package com.eduardonetto.main.controllers.dto;

import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.entities.enums.OrderStatus;

public class OrderDTO {

	private Long id;
	private Integer orderStatus;
	private User client;

	public OrderDTO() {
	}

	public OrderDTO(Order order) {
		this.id = order.getId();
		setOrderStatus(order.getOrderStatus());
		this.client = order.getClient();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

}
