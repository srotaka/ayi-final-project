package com.ayi.academy.app.mappers;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithDetailsRequestDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;
import com.ayi.academy.app.dtos.response.ClientResponsePages;
import com.ayi.academy.app.dtos.response.ClientWithAddressResponseDTO;
import com.ayi.academy.app.dtos.response.ClientWithDetailsResponseDTO;
import com.ayi.academy.app.entities.Client;

import java.util.List;

public interface IClientMapper {
    ClientResponseDTO entityToDto(Client entity);

    ClientWithDetailsResponseDTO clientWithDetailsEntityToDto(Client entity);

    Client clientWithDetailsDtoToEntity(ClientWithDetailsRequestDTO request);

    Client clientWithAddressDtoToEntity(ClientWithAddressRequestDTO request);

    ClientWithAddressResponseDTO clientWithAddressEntityToDto(Client entity);

    ClientResponseDTO entityToDto2(Client entity);

    Client dtoToEntity(ClientRequestDTO requestDTO);

    Client dtoToEntityo2(ClientRequestDTO requestDTO);

    ClientResponsePages pagedClientList(List<Client> clientList);
}
