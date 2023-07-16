package com.eduardonetto.main.controllers.query;

public class ProductToAdd {

	private String productId;
	private Integer quantity;

	public ProductToAdd() {
	}

	public ProductToAdd(String productId, Integer quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductToAdd [pId=" + productId + ", quantity=" + quantity + "]";
	}

}
