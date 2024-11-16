package com.youcode.wrm.service.Implementations;

import com.youcode.wrm.dto.QueueStatisticsDTO;
import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.entity.VisitorStatus;
import com.youcode.wrm.entity.WaitingRoom;
import com.youcode.wrm.repository.VisitRepository;
import com.youcode.wrm.repository.WaitingRoomRepository;
import com.youcode.wrm.service.QueueService;
import com.youcode.wrm.strategy.QueueStrategy;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Validated
public class QueueServiceImpl implements QueueService {

    private final Map<String, QueueStrategy> strategies;
    private final VisitRepository visitRepository;
    private final WaitingRoomRepository waitingRoomRepository;
    @Override
    public Visit getNextVisit(Long waitingRoomId, String strategy) {
        WaitingRoom waitingRoom = waitingRoomRepository.findById(waitingRoomId)
                .orElseThrow(() -> new RuntimeException("WaitingRoom not found"));

        List<Visit> waitingVisits = visitRepository
                .findByWaitingRoomAndStatus(waitingRoom, VisitorStatus.WAITING);

        QueueStrategy queueStrategy = strategies.get(strategy.toLowerCase());
        if (queueStrategy == null) {
            throw new IllegalArgumentException("Invalid queue strategy");
        }

        Visit nextVisit = queueStrategy.getNextVisit(waitingVisits);
        if (nextVisit != null) {
            nextVisit.setStatus(VisitorStatus.IN_PROGRESS);
            nextVisit.setStartTime(LocalDateTime.now());
            return visitRepository.save(nextVisit);
        }

        return null;
    }

    @Override
    public QueueStatisticsDTO getStatistics(Long waitingRoomId) {
        return null;
    }

}
