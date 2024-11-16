package com.youcode.wrm.common;

import com.youcode.wrm.dto.PagedResponse;
import org.springframework.data.domain.Pageable;

public interface CrudService<RequestDTO, ResponseDTO, ID> {
    ResponseDTO save(RequestDTO requestDto);

    ResponseDTO update(ID id, RequestDTO requestDto);

    ResponseDTO findById(ID id);

    PagedResponse<ResponseDTO> findAll(Pageable pageable);

    void deleteById(ID id);
}