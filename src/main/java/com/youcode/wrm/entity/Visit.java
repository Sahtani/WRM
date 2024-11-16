package com.youcode.wrm.entity;

import com.youcode.wrm.entity.Embeddable.VisitId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Visit {

    @EmbeddedId
    private VisitId id;

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

    public long calculateWaitTime() {
        if (arrivalTime != null && startTime != null) {
            return Duration.between(arrivalTime, startTime).toMinutes();
        }
        return 0;
    }
}
