package com.youcode.wrm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Visit> visits = new ArrayList<>();

    /**
     * Add a visit to the visitor's list of visits.
     * Ensures bidirectional synchronization.
     *
     * @param visit the visit to add
     */
    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setVisitor(this);
    }

    /**
     * Remove a visit from the visitor's list of visits.
     * Ensures bidirectional synchronization.
     *
     * @param visit the visit to remove
     */
    public void removeVisit(Visit visit) {
        visits.remove(visit);
        visit.setVisitor(null);
    }
}
