package com.ayi.academy.app.services.impl;

import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponsePages;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.entities.Invoice;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.mappers.IInvoiceMapper;
import com.ayi.academy.app.repositories.InvoiceRepository;
import com.ayi.academy.app.services.IInvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Override
    public List<InvoiceResponseDTO> getAllInvoicesByClientId(Integer clientId)throws ReadAccessException{
        List<InvoiceResponseDTO> responseDTOList = new ArrayList<>();
        List<Invoice> invoiceList = invoiceRepository.getAllInvoicesByIdClient(clientId);
        if(invoiceList == null || invoiceList.size() == 0){
            throw new ReadAccessException("No invoices registered for client ID " + clientId);
        }
        invoiceList.forEach(invoice -> {
            InvoiceResponseDTO invoiceResponseDTO = invoiceMapper.entityToDto(invoice);
            responseDTOList.add(invoiceResponseDTO);
        });
        return responseDTOList;
    }

    @Override
    public InvoiceResponseDTO findInvoiceById(Integer id) throws ReadAccessException {

        if(id == null || id <= 0){
            throw new ReadAccessException("ID is required");
        }

        InvoiceResponseDTO invoiceResponseDTO;
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (!invoice.isPresent()) {
            throw new ReadAccessException("Error. ID not found.");
        }
        return invoiceResponseDTO = invoiceMapper.entityToDto(invoice.get());
    }

    @Override
    public InvoiceResponsePages getPagedInvoices(Integer page, Integer size) throws ReadAccessException {

        InvoiceResponsePages invoiceResponsePages;
        Pageable pageable = PageRequest.of(page, size);
        Page<Invoice> invoicePage = invoiceRepository.findAll(pageable);

        if(invoicePage != null && !invoicePage.isEmpty()) {
            invoiceResponsePages = invoiceMapper.pagedInvoiceList(invoicePage.getContent());
            invoiceResponsePages.setInvoicesPerPage(invoicePage.getSize());
            invoiceResponsePages.setCurrentPage(invoicePage.getNumber() + 1);
            invoiceResponsePages.setTotalPages(invoicePage.getTotalPages());
            invoiceResponsePages.setTotalInvoices((int) invoicePage.getTotalElements());
            return invoiceResponsePages;
        } else {
            throw new ReadAccessException("Error paginating address information");
        }
    }


}
