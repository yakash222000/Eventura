package com.eventura.DTO.Request;

import com.eventura.Model.Event;
import com.eventura.Model.Seat;
import com.eventura.Model.Venue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuyTicketDTO {
    private int ticketID;

    @NotNull(message = "cannot be null")
    private Seat seatType;

    @NotNull(message = "cannot be null")
    private Event event;
}
