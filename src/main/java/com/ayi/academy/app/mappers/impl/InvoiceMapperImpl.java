package com.ayi.academy.app.mappers.impl;
import com.ayi.academy.app.dtos.request.InvoiceRequestDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponsePages;
import com.ayi.academy.app.entities.Invoice;
import com.ayi.academy.app.mappers.IInvoiceMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public InvoiceResponsePages pagedInvoiceList(List<Invoice> invoiceList) {

        InvoiceResponsePages invoiceResponsePages = new InvoiceResponsePages();
        List<InvoiceResponseDTO> addressResponseList = new ArrayList<>();
        invoiceList.forEach((Invoice invoice) -> {
            InvoiceResponseDTO invoiceResponse = new InvoiceResponseDTO(
                    invoice.getInvoiceId(),
                    invoice.getDescription(),
                    invoice.getTotalAmount(),
                    invoice.getClientId()
            );
            addressResponseList.add(invoiceResponse);
        });
        invoiceResponsePages.setInvoiceResponseList(addressResponseList);

        return invoiceResponsePages;
    }
}
