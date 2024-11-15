package com.eventura.Service;

import com.eventura.DTO.Request.LoginRequest;
import com.eventura.DTO.Request.RegisterRequest;
import com.eventura.Model.Role;
import com.eventura.Model.User;
import com.eventura.Repository.RoleRepository;
import com.eventura.Repository.UserRepository;
import com.eventura.Security.CustomerUserDetailsService;
import com.eventura.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;


    @Override
    public String login(LoginRequest loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterRequest registerRequest) {

        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        for (String roleName : registerRequest.getRoles()) {
            Optional<Role> role = roleRepository.findByRoleName(roleName);
            if (role.isPresent()) {
                roles.add(role.get());
            } else {
                throw new RuntimeException("Role " + roleName + " not found");
            }
        }
        user.setRoles(roles);

        userRepository.save(user);

        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(user.getUserName());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()
        );

        return jwtTokenProvider.generateToken(authentication);

    }
}
