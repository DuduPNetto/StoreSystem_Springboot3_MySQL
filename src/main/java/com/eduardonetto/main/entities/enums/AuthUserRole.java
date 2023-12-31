package com.eduardonetto.main.entities.enums;

public enum AuthUserRole {

	ADMIN("admin"), USER("user");

	private String role;

	AuthUserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
