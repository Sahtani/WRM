package com.youcode.wrm.service.Implementations;

import com.youcode.wrm.common.GenericCrudServiceImpl;
import com.youcode.wrm.dto.WaitingRoom.WaitingRoomRequestDTO;
import com.youcode.wrm.dto.WaitingRoom.WaitingRoomResponseDTO;
import com.youcode.wrm.entity.WaitingRoom;
import com.youcode.wrm.repository.WaitingRoomRepository;
import com.youcode.wrm.service.WaitingRoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
@Transactional
public class WaitingRoomServiceImpl extends GenericCrudServiceImpl<WaitingRoom, WaitingRoomRequestDTO, WaitingRoomResponseDTO,Long> implements WaitingRoomService {
}
