package com.youcode.wrm.dto.WaitingRoom;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record WaitingRoomRequestDTO(@NotNull @FutureOrPresent LocalDate date,
                                    @Positive Integer capacity) {
}
