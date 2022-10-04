package com.ayi.academy.app.controllers;

import com.ayi.academy.app.dtos.request.AddressRequestWithoutClientDTO;
import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.ClientWithAddressResponseDTO;
import com.ayi.academy.app.services.IClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value = "Client endpoints", tags = {"Client Controller"})
@RestController
@RequestMapping(value = "/client", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping(value = "/createClient ")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value ="Save a new client with new address", httpMethod = "POST",response = ClientWithAddressRequestDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Client was successfully created.",response = ClientWithAddressRequestDTO.class),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")
    })
    public ResponseEntity<ClientWithAddressRequestDTO> createClient (@RequestBody ClientWithAddressRequestDTO request) {

        ClientWithAddressRequestDTO response = clientService.createClientWithAddress(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
