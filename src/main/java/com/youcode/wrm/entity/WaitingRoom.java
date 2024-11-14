package com.youcode.wrm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class WaitingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate date;
    @OneToMany(mappedBy = "waitingRoom", cascade = CascadeType.ALL)
    private List<Visitor> visitors;

    @NotNull
    private int capacite ;
}