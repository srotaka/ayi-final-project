package com.ayi.academy.app.controllers;

import com.ayi.academy.app.dtos.request.ClientDetailsRequestDTO;
import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithDetailsRequestDTO;
import com.ayi.academy.app.dtos.response.*;
import com.ayi.academy.app.entities.ClientDetails;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.services.IClientService;
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
            List<ClientResponseDTO> responseDTOList = new ArrayList<>();
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

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a client by id and return list of clients", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success. Client deleted by id"),
            @ApiResponse(code = 404, message = "Client not found"),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> deleteAddress(
            @ApiParam(name = "id", required = true, value = "Client ID", example = "1")
            @PathVariable Integer id) throws ReadAccessException {

        Map<String, Object> response = new HashMap<>();
        try {
            response.put("Code", 200);
            response.put("Message", "Client deleted successfully");
            clientService.deleteClient(id);
        } catch (ReadAccessException e) {
            response.put("Error Code", 404);
            response.put("Message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        List<ClientResponseDTO> responseDTOList = clientService.getAllClients();;

        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping(value = "/pagedClientList/{page}/{size}")
    @ApiOperation(value = "Retrieves paged client list", httpMethod = "GET", response = ClientResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success. Paged address list retrieved.", response = ClientResponseDTO[].class),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getAllPersonsForPage(
            @ApiParam(value = "Page to display", required = true, example = "1")
            @PathVariable(name = "page") Integer page,
            @ApiParam(value = "Number of items per request", required = true, example = "2")
            @PathVariable(name = "size") Integer size) {

        ClientResponsePages clientPages;
        Map<String, Object> response = new HashMap<>();

        try {
            clientPages = clientService.getPagedClients(page, size);
        } catch (ReadAccessException e) {
            response.put("Error Code", 404);
            response.put("Message", "No client in data base");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientPages, HttpStatus.OK);
    }


    @PostMapping(value = "/createClient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value ="Save a new client with address and detail information", httpMethod = "POST",response = ClientResponseDTO.class)
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

    @PatchMapping("/updateClient{id}")
    @ApiOperation(value = "Updates any client information needed. No need to update all of the fields", httpMethod = "PATCH", response = ClientWithDetailsResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success. Client updated.", response = ClientWithDetailsResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ClientWithDetailsResponseDTO updateClient(@PathVariable Integer id, @RequestBody Map<String, Object> fields) throws ReadAccessException {

        ClientWithDetailsResponseDTO clientDetailsResponseDTO;
        clientDetailsResponseDTO = clientService.updateClient(id, fields);
        return clientDetailsResponseDTO;
    }




}
