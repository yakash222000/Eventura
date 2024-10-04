package com.eventura.DTO.Response;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private long userId;
    private String username;
    private String email;
    private String token;
    private String message;
}
