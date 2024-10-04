package com.eventura.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class TicketDTO {

    private long ticketId;

    @Valid
    private EventDTO event;

    @Valid
    private UserDTO user;

    @NotBlank(message = "status can not be blank")
    private String status;

    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "seat number must be alphanumeric")
    @NotBlank(message = "seat number can not be blank")
    private String seatNumber;

    @Min(value = 1,message = "ticket price must be positive")
    @NotBlank(message = "price can not be null")
    private long price;

    @NotNull(message = "Date can not be null")
    @PastOrPresent(message = "Date should not be in future")
    private Date date;
}
