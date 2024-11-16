package com.youcode.wrm.dto.Visit;

import java.time.Duration;

public record VisitRequestDTO(Byte priority,
                              Duration estimatedProcessingTim) {
}
