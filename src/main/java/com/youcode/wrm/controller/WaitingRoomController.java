package com.youcode.wrm.controller;

import com.youcode.wrm.common.CrudService;
import com.youcode.wrm.common.GenericController;
import com.youcode.wrm.dto.WaitingRoom.WaitingRoomRequestDTO;
import com.youcode.wrm.dto.WaitingRoom.WaitingRoomResponseDTO;
import com.youcode.wrm.entity.WaitingRoom;
import com.youcode.wrm.service.WaitingRoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/waitingrooms")
public class WaitingRoomController extends GenericController<WaitingRoomRequestDTO, WaitingRoomResponseDTO,Long> {
    public WaitingRoomController(WaitingRoomService service) {
        super(service);
    }
}
