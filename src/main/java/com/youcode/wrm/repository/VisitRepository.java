package com.youcode.wrm.repository;

import com.youcode.wrm.entity.Embeddable.VisitId;
import com.youcode.wrm.entity.Visit;
import com.youcode.wrm.entity.VisitorStatus;
import com.youcode.wrm.entity.WaitingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, VisitId> {
    List<Visit> findByWaitingRoomAndStatus(WaitingRoom waitingRoom, VisitorStatus status);
    int countByWaitingRoomId(Long waitingRoomId);
    @Query("SELECT v FROM Visit v WHERE v.waitingRoom.id = :waitingListId")
    List<Visit> findAllByWaitingListId(@Param("waitingListId") Long waitingListId);

}
