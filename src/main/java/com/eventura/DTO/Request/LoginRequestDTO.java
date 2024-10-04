package com.eventura.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;
}
