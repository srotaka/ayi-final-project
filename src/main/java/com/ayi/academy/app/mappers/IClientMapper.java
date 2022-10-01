package com.ayi.academy.app.mappers;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;
import com.ayi.academy.app.entities.Client;

public interface IClientMapper {
    ClientResponseDTO entityToDto(Client entity);

    Client dtoToEntity(ClientRequestDTO requestDTO);
}
