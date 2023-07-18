package com.eduardonetto.main.controllers.query;

public class UpdateOrderStatus {

	private String status;

	public UpdateOrderStatus() {
	}
	
	public UpdateOrderStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
