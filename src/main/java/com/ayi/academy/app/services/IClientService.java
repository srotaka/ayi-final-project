package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithDetailsRequestDTO;
import com.ayi.academy.app.dtos.response.*;
import com.ayi.academy.app.exceptions.ReadAccessException;

import java.util.List;
import java.util.Map;

public interface IClientService {

    List<ClientResponseDTO> getAllClients() throws ReadAccessException;

    List<ClientBasicResponseDTO> getAllClientsWithBasicInfo() throws ReadAccessException;

    ClientResponseDTO findClientById(Integer id) throws ReadAccessException;

    void deleteClient(Integer id) throws ReadAccessException;

    ClientResponsePages getPagedClients(Integer page, Integer size) throws ReadAccessException;

    ClientResponseDTO createClient(ClientRequestDTO request) throws ReadAccessException;

    ClientWithDetailsResponseDTO updateClient(Integer id, Map<String, Object> fields) throws ReadAccessException;
}
