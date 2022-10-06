package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;
import com.ayi.academy.app.exceptions.ReadAccessException;

import java.util.List;

public interface IClientService {

   // ClientResponseDTO createClient(ClientRequestDTO requestDTO);

    List<ClientResponseDTO> getAllClients() throws ReadAccessException;

    ClientResponseDTO findClientById(Integer id) throws ReadAccessException;
}
