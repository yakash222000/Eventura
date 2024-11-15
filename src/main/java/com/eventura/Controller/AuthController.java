package com.eventura.Controller;

import com.eventura.DTO.Request.LoginRequest;
import com.eventura.DTO.Request.RegisterRequest;
import com.eventura.DTO.Response.AuthResponseDTO;
import com.eventura.Model.Role;
import com.eventura.Model.User;
import com.eventura.Repository.RoleRepository;
import com.eventura.Repository.UserRepository;
import com.eventura.Security.JwtTokenProvider;
import com.eventura.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody LoginRequest loginRequest) {

        String token = authService.login(loginRequest);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(token);

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(@RequestBody RegisterRequest registerRequest) {

        String token = authService.register(registerRequest);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(token);

        return new ResponseEntity<>(authResponseDTO,HttpStatus.OK);
    }
}



