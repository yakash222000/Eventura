package com.eventura.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticketId;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Seat seat;

    private String seatNumber;

    private long price;

    private String status;

    private Date purchaseDate;

}
