package com.ayi.academy.app.services.impl;

import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;
import com.ayi.academy.app.entities.Client;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.mappers.IAddressMapper;
import com.ayi.academy.app.mappers.IClientDetailsMapper;
import com.ayi.academy.app.mappers.IClientMapper;
import com.ayi.academy.app.repositories.ClientRepository;
import com.ayi.academy.app.services.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Slf4j
@Service
@Transactional
public class ClientServiceImpl implements IClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private IClientMapper clientMapper;
    @Autowired
    private IClientDetailsMapper detailsMapper;
    @Autowired
    private IAddressMapper addressMapper;

    @Override
    public List<ClientResponseDTO> getAllClients() throws ReadAccessException {

        List<ClientResponseDTO> responseDTOList;
        List<Client> clientList = clientRepository.findAll();

        if (clientList == null || clientList.size() == 0) {
            throw new ReadAccessException("No clients registered.");
        }
        responseDTOList = clientList.stream().map(client -> new ClientResponseDTO(
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
        )).collect(Collectors.toList());

        return responseDTOList;
    }

    @Override
    public ClientResponseDTO findClientById(Integer id) throws ReadAccessException {
        if(id == null || id <= 0){
            throw new ReadAccessException("Valid ID is required");
        }

        ClientResponseDTO responseDTO = new ClientResponseDTO();
        Client client = clientRepository.findById(id).get();

        if(client == null || client.getClientId() <= 0){
            throw new ReadAccessException("No client registered with ID " + id);
        }


        responseDTO.setClientId(client.getClientId());
        responseDTO.setFirstName(client.getFirstName());
        responseDTO.setLastName(client.getLastName());
        responseDTO.setDni(client.getDni());
        responseDTO.setDocumentType(client.getDocumentType());
        responseDTO.setEmail(client.getEmail());
        responseDTO.setClientDetailsId(detailsMapper.entityToDto(client.getClientDetailsId()));
        responseDTO.setAddressList(client.getAddressList().stream()
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

        return responseDTO;
    }





  /*  @Override
    public ClientResponseDTO createClient(ClientRequestDTO request) {
        //Client client = clientMapper.dtoToEntity(requestDTO);
       // clientRepository.save(client);
       // return clientMapper.entityToDto(client);

        Client client = clientMapper.dtoToEntity(request);
        ClientDetails clientDetail = client.getClientDetailsId();
        //String documentNumber = client.getDocumentNumber();
        List<Address> address = client.getAddressList();

            clientDetail.setClientId(client);
            client.setAddressList(address);
            client = clientRepository.save(client);

            return clientMapper.entityToDto(client);
    }
    */


}
