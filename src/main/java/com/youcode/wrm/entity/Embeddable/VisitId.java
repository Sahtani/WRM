package com.youcode.wrm.entity.Embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class VisitId implements Serializable {
        private Long visitorId;
        private Long waitingRoomId;

        public VisitId(Long visitorId, Long waitingRoomId) {
                this.visitorId = visitorId;
                this.waitingRoomId = waitingRoomId;
        }
}