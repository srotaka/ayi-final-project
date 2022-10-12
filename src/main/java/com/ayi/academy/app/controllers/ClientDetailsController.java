package com.ayi.academy.app.controllers;

import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.ClientDetailsResponseDTO;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.services.IAddressService;
import com.ayi.academy.app.services.IClientDetailsService;
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
@Api(value = "Client Details endpoints", tags = {"Client Details Controller"})
@RestController
@RequestMapping(value = "/details", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ClientDetailsController {

    @Autowired
    private IClientDetailsService detailsService;

    @GetMapping(value = "/getDetailsByClientId/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves details filtered by client ID.", httpMethod = "GET", response = ClientDetailsResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Details retrieved successfully.", response = ClientDetailsResponseDTO.class),
            @ApiResponse(code = 404, message = "No details found."),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getClientDetailsByClientId(
            @ApiParam(name = "id", required = true, value = "Client Id", example = "1")
            @RequestParam Integer id) {
       ClientDetailsResponseDTO responseDTO = new ClientDetailsResponseDTO();
        Map<String, Object> response = new HashMap<>();

        try {
            responseDTO = detailsService.getClientDetailsByClientId(id);
        } catch (ReadAccessException e) {
            response.put("Error Code", 404);
            response.put("Message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(responseDTO);
    }


}
