package com.eventura.Model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long seatId;

    private String seatType;

    @OneToOne
    private Ticket ticket;

}
