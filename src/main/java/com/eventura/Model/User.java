package com.eventura.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String userName;

    @Column(unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private Role role;

    private Date registrationDate;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Ticket> ticketHistory;

    @ManyToMany
    private List<Event> events;
}
