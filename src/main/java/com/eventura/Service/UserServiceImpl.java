package com.eventura.Service;

import com.eventura.DTO.EventDTO;
import com.eventura.DTO.Request.ChangePasswordDTO;
import com.eventura.DTO.Request.LoginRequestDTO;
import com.eventura.DTO.Response.LoginResponseDTO;
import com.eventura.DTO.TicketDTO;
import com.eventura.DTO.UserDTO;
import com.eventura.Exception.*;
import com.eventura.Mapper.EventMapper;
import com.eventura.Mapper.TicketMapper;
import com.eventura.Mapper.UserMapper;
import com.eventura.Model.*;
import com.eventura.Repository.EventRepository;
import com.eventura.Repository.RoleRepository;
import com.eventura.Repository.TicketRepository;
import com.eventura.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    private final TicketRepository ticketRepository;

    private final TicketMapper ticketMapper;

    private final RoleRepository roleRepository;




    @Override
    public UserDTO createUser(UserDTO userDto, String roleName) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateResourceException("Email is already in use.");
        }
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number is already in use.");
        }

        User user = userMapper.toEntity(userDto);

        Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);

        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
        // to be implemented by security
        return null;
    }

    @Override
    public UserDTO updateUser(long userId, UserDTO userDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        existingUser.setUserName(userDto.getUserName());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPhoneNumber(userDto.getPhoneNumber());

        User savedUser = userRepository.save(existingUser);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userMapper.toDTO(user));
        }
        return userDTOs;
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Override
    public void addEvent(long userId, EventDTO eventDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with id " + userId));
        Event event = eventMapper.toEntity(eventDto);
        user.getEvents().add(event);
        userRepository.save(user);
    }

    @Override
    public List<TicketDTO> getTickets(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        List<Ticket> tickets = user.getTicketHistory();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDTOs.add(ticketMapper.toDTO(ticket));
        }
        return ticketDTOs;
    }

    @Override
    public void addTicket(long userId, long eventId, String seatType) {
        Event event = eventRepository.findById(userId).orElseThrow(() -> new EventNotFoundException("Event not found with id " + userId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        Ticket ticket = null;
        for(Ticket t : event.getTickets()){
            if(seatType.equals(t.getSeat())){
                ticket = t;
                event.getTickets().remove(t);
            }
        }
        if(ticket == null){
            throw new NoSeatAvailableException("No seat available for selected type");
        }
        addEvent(userId, eventMapper.toDTO(event));
        user.getTicketHistory().add(ticket);
        userRepository.save(user);
    }

    @Override
    public void cancelTicket(long userId, long ticketId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id "+ ticketId));
        user.getTicketHistory().remove(ticket);
        userRepository.save(user);
    }

    @Override
    public void changePassword(long userId, ChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        user.setPassword(changePasswordDTO.getNewPassword());
        userRepository.save(user);
    }


}
