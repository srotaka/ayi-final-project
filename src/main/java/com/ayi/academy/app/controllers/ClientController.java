package com.ayi.academy.app.controllers;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.services.IClientService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "Client endpoints", tags = {"Client Controller"})
@RestController
@RequestMapping(value = "/client", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ClientController {

    @Autowired
    private IClientService clientService;
    @GetMapping(value = "/getClientList")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves a list with all clients.", httpMethod = "GET", response = ClientResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. List retrieved successfully.", response = ClientResponseDTO[].class),
            @ApiResponse(code = 404, message = "No client found."),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getAllClients() {
        List<ClientResponseDTO> responseDTOList = null;
        Map<String, Object> response = new HashMap<>();

        try {
            responseDTOList = clientService.getAllClients();
        } catch (ReadAccessException e) {
            response.put("Message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping(value = "/getClientById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves a client filtered by ID.", httpMethod = "GET", response = ClientResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Client retrieved successfully.", response = ClientResponseDTO[].class),
            @ApiResponse(code = 404, message = "No client found."),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getClientById(
            @ApiParam(name = "id", required = true, value = "Client Id", example = "1")
            @RequestParam Integer id) {

        Map<String, Object> response = new HashMap<>();

        try {
            return ResponseEntity.ok(clientService.findClientById(id));
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


  /*  @PostMapping(value = "/createClient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value ="Save a new client", httpMethod = "POST",response = ClientResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Client was successfully created",response = ClientResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")
    })
    public ResponseEntity<ClientResponseDTO> createClient (
            @ApiParam(value = "Client data information", required = true)
            @RequestBody ClientRequestDTO  request) {

        ClientResponseDTO responseDTO = clientService.createClient(request);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

   */




}
