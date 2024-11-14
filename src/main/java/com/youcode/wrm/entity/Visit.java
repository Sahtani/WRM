package com.youcode.wrm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime arrivalTime;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private VisitorStatus status;

    private Byte priority;

    private Duration estimatedProcessingTime;

    @ManyToOne
    @JoinColumn(name = "visitor_id", insertable = false, updatable = false)
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "waiting_list_id", insertable = false, updatable = false)
    private WaitingRoom waitingRoom;
}
