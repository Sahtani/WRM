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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@Validated
@RequiredArgsConstructor
public class VisitServiceImpl extends GenericCrudServiceImpl<Visit, VisitRequestDTO, VisitResponseDTO, Long> implements VisitService {

    private final VisitRepository visitRepository;
    private final VisitorRepository visitorRepository;
    private final WaitingRoomRepository waitingRoomRepository;
    private final VisitMapper mapper;

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

}
