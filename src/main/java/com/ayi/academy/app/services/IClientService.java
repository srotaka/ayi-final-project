package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithDetailsRequestDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;
import com.ayi.academy.app.dtos.response.ClientResponsePages;
import com.ayi.academy.app.dtos.response.ClientWithAddressResponseDTO;
import com.ayi.academy.app.dtos.response.ClientWithDetailsResponseDTO;
import com.ayi.academy.app.exceptions.ReadAccessException;

import java.util.List;
import java.util.Map;

public interface IClientService {

   // ClientResponseDTO createClient(ClientRequestDTO requestDTO);

    List<ClientResponseDTO> getAllClients() throws ReadAccessException;

    ClientResponseDTO findClientById(Integer id) throws ReadAccessException;

    void deleteClient(Integer id) throws ReadAccessException;

    ClientResponsePages getPagedClients(Integer page, Integer size) throws ReadAccessException;

    ClientResponseDTO createClient(ClientRequestDTO request);

    ClientWithDetailsResponseDTO updateClient(Integer id, Map<String, Object> fields) throws ReadAccessException;
}
