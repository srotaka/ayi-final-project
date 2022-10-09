package com.ayi.academy.app.mappers.impl;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithDetailsRequestDTO;
import com.ayi.academy.app.dtos.response.*;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.entities.Client;
import com.ayi.academy.app.entities.Invoice;
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

    @Autowired
    private AddressMapperImpl addressMapper;


    @Override
    public ClientResponseDTO entityToDto(Client entity){

        return modelMapper.map(entity, ClientResponseDTO.class);
    }
    @Override
    public Client dtoToEntity(ClientRequestDTO requestDTO){

        return modelMapper.map(requestDTO, Client.class);
    }
    @Override
    public ClientWithDetailsResponseDTO clientWithDetailsEntityToDto(Client entity){
        return modelMapper.map(entity, ClientWithDetailsResponseDTO.class);
    }

    @Override
    public Client clientWithDetailsDtoToEntity(ClientWithDetailsRequestDTO request){
        return modelMapper.map(request, Client.class);
    }

    @Override
    public Client clientWithAddressDtoToEntity(ClientWithAddressRequestDTO request){
        return modelMapper.map(request, Client.class);

    }

    @Override
    public ClientWithAddressResponseDTO clientWithAddressEntityToDto(Client entity){
        return modelMapper.map(entity, ClientWithAddressResponseDTO.class);

    }


    @Override
    public ClientResponseDTO entityToDto2(Client entity){
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        responseDTO.setFirstName(entity.getFirstName());
        responseDTO.setLastName(entity.getLastName());
        responseDTO.setDni(entity.getDni());
        responseDTO.setDocumentType(entity.getDocumentType());
        responseDTO.setEmail(entity.getEmail());
        responseDTO.setClientDetailsId(detailsMapper.entityToDto(entity.getClientDetailsId()));
        responseDTO.setAddressList(entity.getAddressList().stream()
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
                )).collect(Collectors.toList()));
        responseDTO.setInvoiceList(entity.getInvoiceList().stream()
                .map(invoice -> new InvoiceResponseDTO(
                        invoice.getInvoiceId(),
                        invoice.getDescription(),
                        invoice.getTotalAmount(),
                        invoice.getClientId()
                )).collect(Collectors.toList()));


        /*responseDTO.setAddressList(entity.getAddressList().stream()
                .map(address -> modelMapper.map(entity, AddressResponseDTO.class))
                .collect(Collectors.toList()));
        responseDTO.setInvoiceList(entity.getInvoiceList().stream()
                .map(invoice -> modelMapper.map(entity, InvoiceResponseDTO.class))
                .collect(Collectors.toList()));

         */
        return responseDTO;
    }


    @Override
    public Client dtoToEntityo2(ClientRequestDTO requestDTO){
        Client entity = new Client();

        entity.setFirstName(requestDTO.getFirstName());
        entity.setLastName(requestDTO.getLastName());
        entity.setDni(requestDTO.getDni());
        entity.setDocumentType(requestDTO.getDocumentType());
        entity.setEmail(requestDTO.getEmail());
        entity.setClientDetailsId(detailsMapper.dtoToEntity(requestDTO.getClientDetailsId()));
        entity.setAddressList(requestDTO.getAddressList().stream()
                .map(address -> modelMapper.map(requestDTO, Address.class))
                .collect(Collectors.toList()));
        entity.setInvoiceList(requestDTO.getInvoiceList().stream()
                .map(invoice -> modelMapper.map(requestDTO, Invoice.class))
                .collect(Collectors.toList()));


        return entity;
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
                            )).collect(Collectors.toList()),
                    client.getInvoiceList().stream()
                            .map(invoice -> new InvoiceResponseDTO(
                                    invoice.getInvoiceId(),
                                    invoice.getDescription(),
                                    invoice.getTotalAmount(),
                                    invoice.getClientId()
                            )).collect(Collectors.toList())

            );
            clientResponseDTOList.add(responseDTO);
        });
        clientResponsePages.setClientResponseList(clientResponseDTOList);

        return clientResponsePages;
    }

}
