package com.youcode.wrm.entity.Embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VisitId implements Serializable {
        private Long visitorId;
        private Long waitingRoomId;
}