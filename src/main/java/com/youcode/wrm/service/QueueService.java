package com.youcode.wrm.service;

import com.youcode.wrm.dto.QueueStatisticsDTO;
import com.youcode.wrm.entity.Visit;

public interface QueueService {

    public Visit getNextVisit(Long waitingRoomId, String strategy);
    public QueueStatisticsDTO getStatistics(Long waitingRoomId);
}
