package com.eventura.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class VenueDTO {

    private long venueId;

    @NotBlank(message = "name cannot be blank")
    private String venueName;

    @NotBlank(message = "venue cannot be blank ")
    private String venueLocation;

    @Min(value = 50, message = "minimum capacity should be 50")
    private int venueCapacity;

    private List<EventDTO> events;

}
