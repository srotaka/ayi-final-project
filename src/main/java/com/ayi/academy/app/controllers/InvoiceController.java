package com.ayi.academy.app.controllers;

import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.services.IAddressService;
import com.ayi.academy.app.services.IInvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "Invoice endpoints", tags = {"Invoice Controller"})
@RestController
@RequestMapping(value = "/invoice", produces = {MediaType.APPLICATION_JSON_VALUE})
public class InvoiceController {
    @Autowired
    private IInvoiceService invoiceService;

    @GetMapping(value = "/getInvoiceList")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves a list with all invoices.", httpMethod = "GET", response = InvoiceResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. List retrieved successfully.", response = InvoiceResponseDTO[].class),
            @ApiResponse(code = 404, message = "No invoice found."),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getAllInvoices() {
        List<InvoiceResponseDTO> responseDTOList = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();

        try {
            responseDTOList = invoiceService.getAllInvoices();
        } catch (ReadAccessException e) {
            response.put("Message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(responseDTOList);
    }

}
