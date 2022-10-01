package com.ayi.academy.app.mappers.impl;

import com.ayi.academy.app.dtos.request.ClientDetailsRequestDTO;
import com.ayi.academy.app.dtos.response.ClientDetailsResponseDTO;
import com.ayi.academy.app.entities.ClientDetails;
import com.ayi.academy.app.mappers.IClientDetailsMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientDetailsMapperImpl implements IClientDetailsMapper {
    private final ModelMapper modelMapper;

    @Override
    public ClientDetailsResponseDTO entityToDto(ClientDetails entity){

        ClientDetailsResponseDTO responseDTO = new ClientDetailsResponseDTO();
        modelMapper.map(entity, responseDTO);
        return responseDTO;
    }

    @Override
    public ClientDetails dtoToEntity(ClientDetailsRequestDTO requestDTO){

        ClientDetails entity = new ClientDetails();
        modelMapper.map(requestDTO, entity);
        return  entity;
    }

}
