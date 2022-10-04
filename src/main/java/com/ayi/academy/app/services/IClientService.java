package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.response.ClientWithAddressResponseDTO;

public interface IClientService {

    ClientWithAddressRequestDTO createClientWithAddress(ClientWithAddressRequestDTO requestDTO);
}
