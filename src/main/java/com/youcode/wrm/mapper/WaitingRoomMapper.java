package com.youcode.wrm.mapper;

import com.youcode.wrm.common.GenericMapper;
import com.youcode.wrm.dto.WaitingRoom.WaitingRoomRequestDTO;
import com.youcode.wrm.dto.WaitingRoom.WaitingRoomResponseDTO;
import com.youcode.wrm.entity.WaitingRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WaitingRoomMapper  extends GenericMapper<WaitingRoom, WaitingRoomRequestDTO, WaitingRoomResponseDTO> {
}
