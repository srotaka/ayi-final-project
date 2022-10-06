package com.ayi.academy.app.mappers.impl;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;
import com.ayi.academy.app.dtos.response.ClientResponsePages;
import com.ayi.academy.app.entities.Client;
import com.ayi.academy.app.mappers.IClientMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClientMapperImpl implements IClientMapper {
    private final ModelMapper modelMapper;
    @Autowired
    private ClientDetailsMapperImpl detailsMapper;


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

    @Override
    public ClientResponsePages pagedClientList(List<Client> clientList) {

        ClientResponsePages clientResponsePages = new ClientResponsePages();
        List<ClientResponseDTO> clientResponseDTOList = new ArrayList<>();
        clientList.forEach((Client client) -> {
            ClientResponseDTO responseDTO = new ClientResponseDTO(
                    client.getClientId(),
                    client.getFirstName(),
                    client.getLastName(),
                    client.getDni(),
                    client.getDocumentType(),
                    client.getEmail(),
                    detailsMapper.entityToDto(client.getClientDetailsId()),
                    client.getAddressList().stream()
                            .map(address -> new AddressResponseDTO(
                                    address.getAddressId(),
                                    address.getStreet(),
                                    address.getNumber(),
                                    address.getFloor(),
                                    address.getApartmentUnit(),
                                    address.getCity(),
                                    address.getProvince(),
                                    address.getCountry(),
                                    address.getPostalCode(),
                                    address.getClientId()
                            )).collect(Collectors.toList())

            );
            clientResponseDTOList.add(responseDTO);
        });
        clientResponsePages.setClientResponseList(clientResponseDTOList);

        return clientResponsePages;
    }

}
