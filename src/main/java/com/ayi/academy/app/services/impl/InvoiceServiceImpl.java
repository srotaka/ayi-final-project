package com.ayi.academy.app.services.impl;

import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.entities.Invoice;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.mappers.IInvoiceMapper;
import com.ayi.academy.app.repositories.InvoiceRepository;
import com.ayi.academy.app.services.IInvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    IInvoiceMapper invoiceMapper;

    @Override
    public List<InvoiceResponseDTO> getAllInvoices() throws ReadAccessException {

        List<Invoice> invoiceList = invoiceRepository.findAll();
        if (invoiceList == null || invoiceList.size() == 0) {
            throw new ReadAccessException("No invoices registered.");
        }

        List<InvoiceResponseDTO> responseDTOList = new ArrayList<>();

        invoiceList.forEach(client -> {
            InvoiceResponseDTO responseDTO = invoiceMapper.entityToDto(client);
            responseDTOList.add(responseDTO);
        });
        return responseDTOList;
    }


}
