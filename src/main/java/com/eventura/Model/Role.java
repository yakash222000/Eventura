package com.eventura.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.net.ProtocolFamily;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@Enumerated(EnumType.STRING)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}

