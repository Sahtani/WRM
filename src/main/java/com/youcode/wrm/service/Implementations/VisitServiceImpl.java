package com.youcode.wrm.service.Implementations;

import com.youcode.wrm.common.GenericCrudServiceImpl;
import com.youcode.wrm.dto.Visit.VisitRequestDTO;
import com.youcode.wrm.dto.Visit.VisitResponseDTO;
import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.entity.Visitor;
import com.youcode.wrm.entity.VisitorStatus;
import com.youcode.wrm.entity.WaitingRoom;
import com.youcode.wrm.mapper.VisitMapper;
import com.youcode.wrm.repository.VisitRepository;
import com.youcode.wrm.repository.VisitorRepository;
import com.youcode.wrm.repository.WaitingRoomRepository;
import com.youcode.wrm.service.VisitService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@Validated
public class VisitServiceImpl extends GenericCrudServiceImpl<Visit, VisitRequestDTO, VisitResponseDTO, Long> implements VisitService {

    private final VisitRepository visitRepository;
    private final VisitorRepository visitorRepository;
    private final WaitingRoomRepository waitingRoomRepository;
    private final VisitMapper mapper;

    public VisitServiceImpl(VisitRepository visitRepository, VisitorRepository visitorRepository, WaitingRoomRepository waitingRoomRepository, VisitMapper mapper) {
        super(visitRepository, mapper);
        this.visitRepository = visitRepository;
        this.visitorRepository = visitorRepository;
        this.waitingRoomRepository = waitingRoomRepository;
        this.mapper = mapper;
    }

    public VisitResponseDTO save(Long waitingListId, Long visitorId, VisitRequestDTO visitDTO) {
        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new EntityNotFoundException("Visitor not found"));

        WaitingRoom waitingRoom = waitingRoomRepository.findById(waitingListId)
                .orElseThrow(() -> new EntityNotFoundException("WaitingRoom not found"));

        Visit visit = new Visit();
        visit.setVisitor(visitor);
        visit.setWaitingRoom(waitingRoom);
        visit.setArrivalTime(LocalDateTime.now());
        visit.setStatus(VisitorStatus.WAITING);
        visit.setPriority(visitDTO.priority());
        visit.setEstimatedProcessingTime(visitDTO.estimatedProcessingTim());

        Visit savedVisit = visitRepository.save(visit);
        return mapper.toDto(savedVisit);
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
