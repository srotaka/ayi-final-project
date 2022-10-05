package com.ayi.academy.app.mappers.impl;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;
import com.ayi.academy.app.dtos.response.ClientWithAddressResponseDTO;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.entities.Client;
import com.ayi.academy.app.mappers.IClientMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientMapperImpl implements IClientMapper {
    private final ModelMapper modelMapper;

    @Override
    public ClientResponseDTO entityToDto(Client entity){
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        modelMapper.map(entity, responseDTO);
        return responseDTO;
    }

    @Override
    public Client dtoToEntity(ClientRequestDTO requestDTO){
        Client entity = new Client();
        modelMapper.map(requestDTO, entity);
        return  entity;
    }

    public ClientWithAddressResponseDTO requestToResponse(ClientWithAddressRequestDTO requestDTO){
        ClientWithAddressResponseDTO responseDTO = new ClientWithAddressResponseDTO();

        responseDTO.setStreet(requestDTO.getStreet());
        responseDTO.setNumber(requestDTO.getNumber());
        responseDTO.setFloor(requestDTO.getFloor());
        responseDTO.setApartmentUnit(requestDTO.getApartmentUnit());
        responseDTO.setCity(requestDTO.getCity());
        responseDTO.setProvince(requestDTO.getProvince());
        responseDTO.setCountry(requestDTO.getCountry());
        responseDTO.setPostalCode(requestDTO.getPostalCode());
        responseDTO.setFirstName(requestDTO.getFirstName());
        responseDTO.setLastName(requestDTO.getLastName());
        responseDTO.setDni(requestDTO.getDni());
        responseDTO.setDocumentType(requestDTO.getDocumentType());
        responseDTO.setEmail(requestDTO.getEmail());

        return responseDTO;
    }


}
