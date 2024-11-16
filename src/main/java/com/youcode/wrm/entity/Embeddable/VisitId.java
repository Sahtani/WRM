package com.youcode.wrm.entity.Embeddable;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record VisitId(
        @NotNull
        @AttributeOverride(name = "value", column = @Column(name = "visitor_id"))
        Long visitorId,

        @NotNull
        @AttributeOverride(name = "value", column = @Column(name = "waiting_list_id"))
        Long waitingListId) {
}