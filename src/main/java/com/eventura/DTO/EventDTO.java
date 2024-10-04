package com.eventura.DTO;

import com.eventura.Model.EventType;
import com.eventura.Model.User;
import com.eventura.Model.Venue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDTO {

    private long eventId;

    @NotBlank(message = "name can not be blank")
    private String eventName;

    @Size(max = 500, message = "Size cannot exceed 500 characters")
    private String eventDescription;

    @NotNull(message = "Time cannot be null")
    private LocalDateTime startTime;

    @NotNull(message = "Time cannot be null")
    private LocalDateTime endTime;

    @NotNull(message = "Type cannot be null")
    private EventType eventType;

    @Valid
    private VenueDTO venueDTO;

    private List<UserDTO> userDTOS;

    private List<TicketDTO> ticketDTOS;


}
