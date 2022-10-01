package com.ayi.academy.app.mappers;

import com.ayi.academy.app.dtos.request.BillRequestDTO;
import com.ayi.academy.app.dtos.response.BillResponseDTO;
import com.ayi.academy.app.entities.Bill;

public interface IBillMapper {
    BillResponseDTO entityToDto(Bill entity);

    Bill dtoToEntity(BillRequestDTO requestDTO);
}
