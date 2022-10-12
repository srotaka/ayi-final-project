package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.request.InvoiceRequestWithoutClientDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponsePages;
import com.ayi.academy.app.exceptions.ReadAccessException;

import java.util.List;
import java.util.Map;

public interface IInvoiceService {
    List<InvoiceResponseDTO> getAllInvoices() throws ReadAccessException;

    List<InvoiceResponseDTO> getAllInvoicesByClientId(Integer clientId)throws ReadAccessException;

    InvoiceResponseDTO findInvoiceById(Integer id) throws ReadAccessException;

    InvoiceResponsePages getPagedInvoices(Integer page, Integer size) throws ReadAccessException;

    InvoiceResponseDTO createInvoice (InvoiceRequestWithoutClientDTO request, Integer clientId) throws ReadAccessException;
}
