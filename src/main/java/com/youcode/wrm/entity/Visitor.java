package com.youcode.wrm.entity;

import jakarta.persistence.*;

@Entity
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "visitor")
    private List<Visit> visits = new ArrayList<>();
}