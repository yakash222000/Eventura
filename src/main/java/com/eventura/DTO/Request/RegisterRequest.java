package com.eventura.DTO.Request;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
    private Set<String> roles;
}

