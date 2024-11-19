package com.youcode.wrm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WaitingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @OneToMany(mappedBy = "waitingRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Visit> visits = new HashSet<>();

    @NotNull
    @Positive
    private int capacity;

    @Column(length = 50)
    private String mode;

    /**
     * Add a visit to the waiting room's list of visits.
     * Ensures bidirectional synchronization.
     *
     * @param visit the visit to add
     */
    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setWaitingRoom(this);
    }

    /**
     * Remove a visit from the waiting room's list of visits.
     * Ensures bidirectional synchronization.
     *
     * @param visit the visit to remove
     */
    public void removeVisit(Visit visit) {
        visits.remove(visit);
        visit.setWaitingRoom(null);
    }
}
