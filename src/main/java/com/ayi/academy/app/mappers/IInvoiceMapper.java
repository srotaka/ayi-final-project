package com.ayi.academy.app.mappers;

import com.ayi.academy.app.dtos.request.InvoiceRequestDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponsePages;
import com.ayi.academy.app.entities.Invoice;

import java.util.List;

public interface IInvoiceMapper {
    InvoiceResponseDTO entityToDto(Invoice entity);

    Invoice dtoToEntity(InvoiceRequestDTO requestDTO);

    InvoiceResponsePages pagedInvoiceList(List<Invoice> invoiceList);
}
