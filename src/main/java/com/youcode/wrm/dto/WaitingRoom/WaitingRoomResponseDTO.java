package com.youcode.wrm.dto.WaitingRoom;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record WaitingRoomResponseDTO(@NotNull Long id,
                                     @NotNull LocalDate date,
                                     @NotNull Integer capacity) {
}
