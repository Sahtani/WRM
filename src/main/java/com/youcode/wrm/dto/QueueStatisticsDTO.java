package com.youcode.wrm.dto;

import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.entity.WaitingRoom;

import java.util.List;

public record QueueStatisticsDTO(WaitingRoom waitingRoom,
                                       List<Visit> visits,
                                       double averageWaitTime,
                                       int rotation) {
}
