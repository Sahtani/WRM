package com.youcode.wrm.service;

import com.youcode.wrm.common.CrudService;
import com.youcode.wrm.dto.Visit.VisitRequestDTO;
import com.youcode.wrm.dto.Visit.VisitResponseDTO;
import com.youcode.wrm.entity.Embeddable.VisitId;

public interface VisitService extends CrudService<VisitRequestDTO, VisitResponseDTO, VisitId> {
}
