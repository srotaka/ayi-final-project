package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;

public interface IClientService {
    ClientResponseDTO createClient(ClientRequestDTO requestDTO);
}
