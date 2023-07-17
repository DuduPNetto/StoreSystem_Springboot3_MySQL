package com.eduardonetto.main.controllers.dto.auth;

import com.eduardonetto.main.entities.enums.AuthUserRole;

public record RegisterDTO(String username, String password, String name, String email, AuthUserRole role) {

}
