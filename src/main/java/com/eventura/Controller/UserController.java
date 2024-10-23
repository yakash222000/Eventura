package com.eventura.Controller;
import com.eventura.DTO.Request.ChangePasswordDTO;
import com.eventura.DTO.Request.LoginRequestDTO;
import com.eventura.DTO.Response.LoginResponseDTO;
import com.eventura.DTO.TicketDTO;
import com.eventura.DTO.UserDTO;
import com.eventura.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
//        UserDTO createdUser = userService.createUser(userDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDTO> login(@Validated @RequestBody LoginRequestDTO loginRequestDTO) {
//        LoginResponseDTO loginResponseDTO = userService.loginUser(loginRequestDTO);
//        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable long id) {
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id,@Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable long id, @Valid @RequestBody ChangePasswordDTO changePasswordDTO) {

        if (!changePasswordDTO.isPasswordMatching()) {
            return ResponseEntity.badRequest().body("New password and confirmation do not match.");
        }

        userService.changePassword(id, changePasswordDTO);
        return ResponseEntity.ok("Password changed successfully.");
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PostMapping("/{id}/{eventId}/{seatType}")
    public ResponseEntity<String> addTicket(@PathVariable long id,@PathVariable long eventId,@PathVariable String seatType) {
        userService.addTicket(id, eventId, seatType);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ticket added successfully.");
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<TicketDTO>> getTickets(@PathVariable long id) {
        List<TicketDTO> ticketDTOS = userService.getTickets(id);
        return ResponseEntity.status(HttpStatus.OK).body(ticketDTOS);
    }

    @DeleteMapping("/{userId}/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable long userId, @PathVariable long ticketId) {
        userService.cancelTicket(userId, ticketId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ticket deleted successfully.");
    }

}
