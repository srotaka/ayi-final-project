package com.ayi.academy.app.mappers;

import com.ayi.academy.app.dtos.request.ClientDetailsRequestDTO;
import com.ayi.academy.app.dtos.response.ClientDetailsResponseDTO;
import com.ayi.academy.app.entities.ClientDetails;

public interface IClientDetailsMapper {
    ClientDetailsResponseDTO entityToDto(ClientDetails entity);

    ClientDetails dtoToEntity(ClientDetailsRequestDTO requestDTO);
}
