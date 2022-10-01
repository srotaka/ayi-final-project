package com.ayi.academy.app.mappers.impl;
import com.ayi.academy.app.dtos.request.BillRequestDTO;
import com.ayi.academy.app.dtos.response.BillResponseDTO;
import com.ayi.academy.app.entities.Bill;
import com.ayi.academy.app.mappers.IBillMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BillMapperImpl implements IBillMapper {
    private final ModelMapper modelMapper;

    @Override
    public BillResponseDTO entityToDto(Bill entity){

        BillResponseDTO responseDTO = new BillResponseDTO();
        modelMapper.map(entity, responseDTO);
        return responseDTO;
    }

    @Override
    public Bill dtoToEntity(BillRequestDTO requestDTO){

        Bill entity = new Bill();
        modelMapper.map(requestDTO, entity);
        return  entity;
    }
}
