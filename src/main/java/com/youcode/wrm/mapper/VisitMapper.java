package com.youcode.wrm.mapper;

import com.youcode.wrm.common.GenericMapper;
import com.youcode.wrm.dto.Visit.VisitRequestDTO;
import com.youcode.wrm.dto.Visit.VisitResponseDTO;
import com.youcode.wrm.entity.Visit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitMapper extends GenericMapper<Visit, VisitRequestDTO, VisitResponseDTO> {
}
