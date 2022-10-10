package com.ayi.academy.app.controllers;

import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.AddressResponsePages;
import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.dtos.response.InvoiceResponsePages;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.services.IAddressService;
import com.ayi.academy.app.services.IInvoiceService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/getInvoicesByClientId/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves a list with all invoices filtered by client ID.", httpMethod = "GET", response = InvoiceResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. List retrieved successfully.", response = InvoiceResponseDTO[].class),
            @ApiResponse(code = 404, message = "No invoice found."),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getAllInvoicesByClientId(
            @ApiParam(name = "id", required = true, value = "Client Id", example = "1")
            @RequestParam Integer id) {
        List<InvoiceResponseDTO> responseDTOList = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();

        try {
            responseDTOList = invoiceService.getAllInvoicesByClientId(id);
        } catch (ReadAccessException e) {
            response.put("Error Code", 404);
            response.put("Message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping(value = "/getInvoiceById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves an invoice filtered by ID.", httpMethod = "GET", response = InvoiceResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Invoice received successfully.", response = InvoiceResponseDTO[].class),
            @ApiResponse(code = 404, message = "No invoice found."),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getInvoiceById(
            @ApiParam(name = "id", required = true, value = "Invoice Id", example = "1")
            @RequestParam Integer id) {

        Map<String, Object> response = new HashMap<>();

        try {
            return ResponseEntity.ok(invoiceService.findInvoiceById(id));
        } catch (ReadAccessException e) {
            response.put("Error code", 404);
            response.put("Message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("Error Code", 400);
            response.put("Message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/pagedInvoiceList/{page}/{size}")
    @ApiOperation(value = "Retrieves paged invoice list", httpMethod = "GET", response = InvoiceResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success. Paged invoice list retrieved.", response = InvoiceResponseDTO[].class),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getInvoicesForPage(
            @ApiParam(value = "Page to display", required = true, example = "1")
            @PathVariable(name = "page") Integer page,
            @ApiParam(value = "Number of items per request", required = true, example = "3")
            @PathVariable(name = "size") Integer size) {

        InvoiceResponsePages invoicePages;
        Map<String, Object> response = new HashMap<>();

        try {
            invoicePages = invoiceService.getPagedInvoices(page, size);
        } catch (ReadAccessException e) {
            response.put("Error Code", 404);
            response.put("Message", "No address in data base");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(invoicePages, HttpStatus.OK);
    }

}
