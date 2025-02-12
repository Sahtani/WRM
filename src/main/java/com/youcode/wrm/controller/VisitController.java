package com.youcode.wrm.controller;

import com.youcode.wrm.common.GenericController;
import com.youcode.wrm.dto.Visit.VisitRequestDTO;
import com.youcode.wrm.dto.Visit.VisitResponseDTO;
import com.youcode.wrm.entity.Embeddable.VisitId;
import com.youcode.wrm.service.VisitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visits")
public class VisitController extends GenericController<VisitRequestDTO, VisitResponseDTO, VisitId> {
    public VisitController(VisitService service) {
        super(service);
    }
}
