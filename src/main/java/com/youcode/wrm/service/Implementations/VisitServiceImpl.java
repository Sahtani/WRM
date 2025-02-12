package com.youcode.wrm.service.Implementations;

import com.youcode.wrm.common.GenericCrudServiceImpl;
import com.youcode.wrm.dto.Visit.VisitRequestDTO;
import com.youcode.wrm.dto.Visit.VisitResponseDTO;
import com.youcode.wrm.dto.WaitingRoom.WaitingRoomResponseDTO;
import com.youcode.wrm.entity.Embeddable.VisitId;
import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.exception.WaitingRoomFullException;
import com.youcode.wrm.mapper.VisitMapper;
import com.youcode.wrm.repository.VisitRepository;
import com.youcode.wrm.repository.VisitorRepository;
import com.youcode.wrm.service.VisitService;
import com.youcode.wrm.service.WaitingRoomService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class VisitServiceImpl extends GenericCrudServiceImpl<Visit, VisitRequestDTO, VisitResponseDTO, VisitId> implements VisitService {

    private final VisitRepository visitRepository;
    private final VisitorRepository visitorRepository;
    private final WaitingRoomService waitingRoomService;
    private final VisitMapper mapper;

    @Value("${spring.app.default.capacity}")
    private int defaultCapacity;

    public VisitServiceImpl(VisitRepository visitRepository, VisitorRepository visitorRepository, WaitingRoomService waitingRoomService, VisitMapper mapper) {
        super(visitRepository, mapper);
        this.visitRepository = visitRepository;
        this.visitorRepository = visitorRepository;
        this.waitingRoomService = waitingRoomService;
        this.mapper = mapper;
    }

    public VisitResponseDTO save(VisitRequestDTO visitRequestDTO) {
        // Validate input
        if (visitRequestDTO == null) {
            throw new IllegalArgumentException("Visit request cannot be null");
        }

        if (visitRequestDTO.id() == null) {
            throw new IllegalArgumentException("Visit ID cannot be null in the request");
        }

        Long waitingRoomId = visitRequestDTO.id().waitingRoomId();

        if (waitingRoomId == null) {
            throw new IllegalArgumentException("Waiting Room ID cannot be null in the request");
        }

        // Fetch waiting room
        WaitingRoomResponseDTO waitingRoom = waitingRoomService.findById(waitingRoomId);

        if (waitingRoom == null) {
            throw new EntityNotFoundException("Waiting Room not found with ID: " + waitingRoomId);
        }

        // Check waiting room capacity
        int maxCapacity = waitingRoom.capacity() != null ? waitingRoom.capacity() : defaultCapacity;
        checkWaitingRoomCapacity(waitingRoomId, maxCapacity);

        // Map DTO to entity and save
        Visit visit = mapper.toEntity(visitRequestDTO);

        Visit savedVisit = visitRepository.save(visit);

        // Map saved entity to response DTO
        return mapper.toDto(savedVisit);
    }

    private void checkWaitingRoomCapacity(Long waitingRoomId, int maxCapacity) {
        int currentVisits = visitRepository.countByWaitingRoomId(waitingRoomId);
        if (currentVisits >= maxCapacity) {
            throw new WaitingRoomFullException(
                    String.format("The waiting room (id: %d) has reached its maximum capacity of %d",
                            waitingRoomId, maxCapacity)
            );
        }
    }

//    // Méthode pour obtenir le prochain visiteur selon l'algorithme FIFO
//    public VisitResponseDTO getNextVisitorFIFO(Long waitingRoomId) {
//        WaitingRoom waitingRoom = waitingRoomRepository.findById(waitingRoomId)
//                .orElseThrow(() -> new EntityNotFoundException("WaitingRoom not found"));
//
//        return visitRepository.findByWaitingRoomAndStatus(waitingRoom, VisitorStatus.WAITING)
//                .stream()
//                .min(Comparator.comparing(Visit::getArrivalTime))
//                .map(mapper::toDto)
//                .orElseThrow(NoVisitorsWaitingException::new);
//    }
//
//    // Méthode pour obtenir le prochain visiteur selon la priorité
//    public VisitResponseDTO getNextVisitorByPriority(Long waitingRoomId) {
//        WaitingRoom waitingRoom = waitingRoomRepository.findById(waitingRoomId)
//                .orElseThrow(() -> new EntityNotFoundException("WaitingRoom not found"));
//
//        return visitRepository.findByWaitingRoomOrderByPriorityDescArrivalTimeAsc(waitingRoom)
//                .stream()
//                .filter(visit -> visit.getStatus() == VisitorStatus.WAITING)
//                .findFirst()
//                .map(mapper::toDto)git remote add origin https://github.com/username/repo.git
//                .orElseThrow(NoVisitorsWaitingException::new);
//    }
//
//    // Méthode pour obtenir le prochain visiteur selon SJF
//    public VisitResponseDTO getNextVisitorSJF(Long waitingRoomId) {
//        WaitingRoom waitingRoom = waitingRoomRepository.findById(waitingRoomId)
//                .orElseThrow(() -> new EntityNotFoundException("WaitingRoom not found"));
//
//        return visitRepository.findByWaitingRoomOrderByEstimatedProcessingTimeAsc(waitingRoom)
//                .stream()
//                .filter(visit -> visit.getStatus() == VisitorStatus.WAITING)
//                .findFirst()
//                .map(visit -> mapper.toDto(visit,))
//                .orElseThrow(NoVisitorsWaitingException::new);
//    }

}
