package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.exceptions.ReadAccessException;

import java.util.List;

public interface IInvoiceService {
    List<InvoiceResponseDTO> getAllInvoices() throws ReadAccessException;
}
