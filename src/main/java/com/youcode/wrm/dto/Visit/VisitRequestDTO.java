package com.youcode.wrm.dto.Visit;

import com.youcode.wrm.entity.VisitorStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;

public record VisitRequestDTO(VisitIDDTO id,
                              @NotNull LocalDateTime arrivalTime,

                              LocalDateTime startTime,

                              LocalDateTime endTime,

                              @Enumerated(EnumType.STRING) VisitorStatus status,
                              Byte priority,
                              Duration estimatedProcessingTim) {
}