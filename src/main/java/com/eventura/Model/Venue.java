package com.eventura.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long venueId;

    private String venueName;

    private String venueLocation;

    private int venueCapacity;

    @OneToMany(mappedBy = "venue")
    private List<Event> events;
}
