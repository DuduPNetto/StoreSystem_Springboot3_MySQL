package com.eduardonetto.main.controllers.dto;

import com.eduardonetto.main.entities.Product;

public class ProductDTO {

	private Long id;
	private String name;
	private double price;

	public ProductDTO() {
	}

	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
