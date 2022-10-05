package com.ayi.academy.app.controllers;

import com.ayi.academy.app.dtos.request.AddressRequestWithoutClientDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.AddressResponsePages;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.services.IAddressService;
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
@Api(value = "Address endpoints", tags = {"Address Controller"})
@RestController
@RequestMapping(value = "/address", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping(value = "/addAddress ")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Save an address with an existing client.", httpMethod = "POST", response = AddressResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Address was successfully created.", response = AddressResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")
    })
    public ResponseEntity<AddressResponseDTO> addPerson(@RequestBody AddressRequestWithoutClientDTO request, Integer clientId) {
        AddressResponseDTO addressResponse = addressService.createAddress(request, clientId);
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAddressList")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves a list with all addresses.", httpMethod = "GET", response = AddressResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. List retrieved successfully.", response = AddressResponseDTO[].class),
            @ApiResponse(code = 404, message = "No address found."),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getAllAddress() {
        List<AddressResponseDTO> responseDTOList = null;
        Map<String, Object> response = new HashMap<>();

        try {
            responseDTOList = addressService.getAllAddressWithoutClient();
        } catch (ReadAccessException e) {
            response.put("Message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping(value = "/getAddressById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves an addresses filtered by ID.", httpMethod = "GET", response = AddressResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. List retrieved successfully.", response = AddressResponseDTO[].class),
            @ApiResponse(code = 404, message = "No address found."),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getAddressById(
            @ApiParam(name = "id", required = true, value = "Address Id", example = "1")
            @RequestParam Integer id) {

        Map<String, Object> response = new HashMap<>();

        try {
            return ResponseEntity.ok(addressService.findAddressById(id));
        } catch (ReadAccessException e) {
            response.put("Código de error", 404);
            response.put("Mensaje", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("Error Code", 400);
            response.put("Message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAddressByClientId/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves a list with all addresses filtered by client ID.", httpMethod = "GET", response = AddressResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. List retrieved successfully.", response = AddressResponseDTO[].class),
            @ApiResponse(code = 404, message = "No address found."),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getAllAddressByClientId(
            @ApiParam(name = "id", required = true, value = "Client Id", example = "1")
            @RequestParam Integer id) {
        List<AddressResponseDTO> responseDTOList = null;
        Map<String, Object> response = new HashMap<>();

        try {
            responseDTOList = addressService.getAllAddressByClientId(id);
        } catch (ReadAccessException e) {
            response.put("Error Code", 404);
            response.put("Message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(responseDTOList);
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete an address by id", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success. Address deleted by id"),
            @ApiResponse(code = 404, message = "Address not found"),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> deleteAddress(
            @ApiParam(name = "id", required = true, value = "Address ID", example = "1")
            @PathVariable Integer id) {

        Map<String, Object> response = new HashMap<>();
        try {
            response.put("Code", 204);
            response.put("Message", "Address deleted successfully");
            addressService.deleteAddress(id);
        } catch (ReadAccessException e) {
            response.put("Error Code", 404);
            response.put("Message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/pagedAddressList/{page}/{size}")
    @ApiOperation(value = "Retrieves paged address list", httpMethod = "GET", response = AddressResponseDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success. Paged address list retrieved.", response = AddressResponseDTO[].class),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public ResponseEntity<?> getAllPersonsForPage(
            @ApiParam(value = "Page to display", required = true, example = "1")
            @PathVariable(name = "page") Integer page,
            @ApiParam(value = "Number of items per request", required = true, example = "3")
            @PathVariable(name = "size") Integer size) {

        AddressResponsePages addressPages;
        Map<String, Object> response = new HashMap<>();

        try {
            addressPages = addressService.getPagedAddresses(page, size);
        } catch (ReadAccessException e) {
            response.put("Error Code", 404);
            response.put("Message", "No address in data base");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(addressPages, HttpStatus.OK);
    }

    @PatchMapping("/updateAddress/{id}")
    @ApiOperation(value = "Updates address information", httpMethod = "PATCH", response = AddressResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success. Address updated.", response = AddressResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request/Invalid field")})
    public AddressResponseDTO updateAddress(@PathVariable Integer id, @RequestBody Map<String, Object> fields) throws ReadAccessException {

        AddressResponseDTO addressResponseDTO;
        addressResponseDTO = addressService.updateAddress(id, fields);
        return addressResponseDTO;
    }


}
