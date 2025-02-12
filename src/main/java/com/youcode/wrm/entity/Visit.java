package com.youcode.wrm.entity;

import com.youcode.wrm.entity.Embeddable.VisitId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.time.Duration;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitorStatus status;

    private Byte priority;

    @Column(nullable = false)
    private Duration estimatedProcessingTime;

    @ManyToOne
    @MapsId("visitorId")
    private Visitor visitor;

    @ManyToOne
    @MapsId("waitingRoomId")
    private WaitingRoom waitingRoom;

    public long calculateWaitTime() {
        if (arrivalTime != null && startTime != null) {
            return Duration.between(arrivalTime, startTime).toMinutes();
        }
        return 0;
    }
}