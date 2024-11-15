package com.eventura.Service;

import com.eventura.DTO.Request.LoginRequest;
import com.eventura.DTO.Request.RegisterRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);

    String register(RegisterRequest registerRequest);

}
