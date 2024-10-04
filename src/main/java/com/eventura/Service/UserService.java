package com.eventura.Service;

import com.eventura.DTO.EventDTO;
import com.eventura.DTO.Request.ChangePasswordDTO;
import com.eventura.DTO.Request.LoginRequestDTO;
import com.eventura.DTO.Response.LoginResponseDTO;
import com.eventura.DTO.TicketDTO;
import com.eventura.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDto);

    LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO);

    UserDTO updateUser(long id, UserDTO userDto);

    UserDTO getUserById(long id);

    UserDTO getUserByEmail(String email);

    List<UserDTO> getAllUsers();

    void deleteUser(long id);

    void addEvent(long userId, EventDTO eventDto);

    List<TicketDTO> getTickets(long userId);

    void addTicket(long userId, long eventId, String seatType);

    void cancelTicket(long userId, long ticketId);

    void changePassword(long userId, ChangePasswordDTO changePasswordDTO);
}
