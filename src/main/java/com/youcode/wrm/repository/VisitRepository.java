package com.youcode.wrm.repository;

import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.entity.VisitorStatus;
import com.youcode.wrm.entity.WaitingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByWaitingRoomAndStatus(WaitingRoom waitingRoom, VisitorStatus status);
    List<Visit> findByWaitingRoomOrderByPriorityDescArrivalTimeAsc(WaitingRoom waitingRoom);
    List<Visit> findByWaitingRoomOrderByEstimatedProcessingTimeAsc(WaitingRoom waitingRoom);
}
