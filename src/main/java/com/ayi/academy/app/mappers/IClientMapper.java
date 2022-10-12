package com.ayi.academy.app.mappers;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithDetailsRequestDTO;
import com.ayi.academy.app.dtos.response.*;
import com.ayi.academy.app.entities.Client;

import java.util.List;

public interface IClientMapper {
    ClientResponseDTO entityToDto(Client entity);

    ClientWithDetailsResponseDTO clientWithDetailsEntityToDto(Client entity);

    Client clientWithDetailsDtoToEntity(ClientWithDetailsRequestDTO request);

    Client clientWithAddressDtoToEntity(ClientWithAddressRequestDTO request);

    ClientWithAddressResponseDTO clientWithAddressEntityToDto(Client entity);

    ClientBasicResponseDTO entityBasicToDto(Client entity);

    Client dtoToEntity(ClientRequestDTO requestDTO);

    Client newClientDtoToEntity(ClientRequestDTO requestDTO);

    ClientResponsePages pagedClientList(List<Client> clientList);
}
