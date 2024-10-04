package com.eventura.DTO;

import com.eventura.Model.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDTO {

    private long userId;

    @NotBlank(message = "User can not be blank")
    private String userName;

    @NotBlank(message = "Password can not be blank")
    private String password;

    @Email(message = "Email not valid")
    @NotBlank(message = "email can not be empty")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotNull(message = "Role is required")
    private Role role;

    @PastOrPresent(message = "Registration date cannot be in the future")
    private Date registrationDate;
}
