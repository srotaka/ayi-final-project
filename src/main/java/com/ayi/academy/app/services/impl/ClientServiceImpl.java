package com.ayi.academy.app.services.impl;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.entities.Client;
import com.ayi.academy.app.entities.ClientDetails;
import com.ayi.academy.app.mappers.IClientMapper;
import com.ayi.academy.app.repositories.AddressRepository;
import com.ayi.academy.app.repositories.ClientDetailsRepository;
import com.ayi.academy.app.repositories.ClientRepository;
import com.ayi.academy.app.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private IClientMapper clientMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientDetailsRepository detailsRepository;


    @Override
    public ClientResponseDTO createClient(ClientRequestDTO requestDTO){
        Client client = clientMapper.dtoToEntity(requestDTO);
        ClientDetails details = client.getClientDetailsId();
        List<Address> addressList = client.getAddressList();
        details.setClientId(client);
        client.setAddressList(addressList);

        for (Address address: addressList) {
            address.setClientId(client);
            address = addressRepository.save(address);
        }

        details = detailsRepository.save(details);
        client = clientRepository.save(client);
        return clientMapper.entityToDto(client);
    }

}
