package com.eduardonetto.main.controllers.dto;

import java.util.ArrayList;
import java.util.List;

import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.entities.User;

public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private List<Order> orders = new ArrayList<>();

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.orders = user.getOrders();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Order> getOrders() {
		return orders;
	}

}
