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
import java.util.stream.Collectors;

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
        WaitingRoom waitingRoom = waitingRoomRepository.findById(waitingRoomId)
                .orElseThrow(() -> new RuntimeException("WaitingRoom not found"));

        List<Visit> visits = visitRepository.findByWaitingRoom(waitingRoom);

        List<Visit> finishedVisits = visits.stream()
                .filter(visit -> "finished".equalsIgnoreCase(visit.getStatus().toString()))
                .toList();

        double averageWaitTime = finishedVisits.stream()
                .mapToLong(Visit::calculateWaitTime)
                .average()
                .orElse(0);

        // Calculer la rotation des visiteurs (nombre total de visiteurs traités)
        int totalVisitors = finishedVisits.size();

        // Nombre total de places disponibles dans la salle d'attente
        int totalSeats = waitingRoom.getCapacity();

        // Calculer le taux de rotation
        double rotationRate = totalVisitors > 0 ? (double) totalSeats / totalVisitors : 0;

        return new QueueStatisticsDTO(
                waitingRoom,
                visits,
                averageWaitTime,
                rotationRate
        );
    }

}
