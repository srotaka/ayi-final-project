package com.ayi.academy.app.mappers.impl;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithAddressRequestDTO;
import com.ayi.academy.app.dtos.request.ClientWithDetailsRequestDTO;
import com.ayi.academy.app.dtos.response.*;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.entities.Client;
import com.ayi.academy.app.entities.ClientDetails;
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
    @Autowired
    private InvoiceMapperImpl invoiceMapper;


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
    public Client newClientDtoToEntity(ClientRequestDTO requestDTO){
        Client entity = new Client();
        ClientDetails details = detailsMapper.dtoToEntity(requestDTO.getClientDetailsId());
        Address address = addressMapper.dtoToEntity(requestDTO.getAddressList().get(0));
        Invoice invoice = invoiceMapper.dtoToEntity(requestDTO.getInvoiceList().get(0));

        details.setClientId(entity);
        address.setClientId(entity);
        invoice.setClientId(entity);
        entity.setClientDetailsId(details);
        entity.getAddressList().add(address);
        entity.getInvoiceList().add(invoice);

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
