package com.eventura.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eventId;

    private String eventName;

    private String eventDescription;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private EventType eventType;

    @ManyToOne
    @JoinColumn(name="venue_id")
    private Venue venue;

    @ManyToMany(mappedBy = "events")
    private List<User> users;

    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;
}
