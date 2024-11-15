package com.youcode.wrm.repository;

import com.youcode.wrm.entity.Visitor;
import com.youcode.wrm.entity.VisitorStatus;
import com.youcode.wrm.entity.WaitingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByWaitingRoomAndStatus(WaitingRoom waitingRoom, VisitorStatus status);
}
