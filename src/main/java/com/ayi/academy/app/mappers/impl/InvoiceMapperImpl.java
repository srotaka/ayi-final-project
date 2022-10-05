package com.ayi.academy.app.mappers.impl;
import com.ayi.academy.app.dtos.request.InvoiceRequestDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.entities.Invoice;
import com.ayi.academy.app.mappers.IInvoiceMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceMapperImpl implements IInvoiceMapper {
    private final ModelMapper modelMapper;

    @Override
    public InvoiceResponseDTO entityToDto(Invoice entity){

        InvoiceResponseDTO responseDTO = new InvoiceResponseDTO();
        modelMapper.map(entity, responseDTO);
        return responseDTO;
    }

    @Override
    public Invoice dtoToEntity(InvoiceRequestDTO requestDTO){

        Invoice entity = new Invoice();
        modelMapper.map(requestDTO, entity);
        return  entity;
    }
}
