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
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ClientServiceImpl implements IClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private IClientMapper clientMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientDetailsRepository detailsRepository;
    MessageUtil logger;

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO requestDTO) {
        Client client = clientMapper.dtoToEntity(requestDTO);
       /* List<Address> addressList = client.getAddressList();
        ClientDetails detailsList = detailsRepository.save(client.getClientDetailsId());

        for (Address address : client.getAddressList()) {
            address = addressRepository.save(address);
        }
*/
        client = clientRepository.save(client);

        return clientMapper.entityToDto(client);
    }

    /* @Override
    public ClientWithAddressRequestDTO createClientWithAddress(ClientWithAddressRequestDTO requestDTO) {

         Client client = new Client();
         Address address = new Address();

         address.setStreet(requestDTO.getStreet());
         address.setNumber(requestDTO.getNumber());
         address.setFloor(requestDTO.getFloor());
         address.setApartmentUnit(requestDTO.getApartmentUnit());
         address.setCity(requestDTO.getCity());
         address.setProvince(requestDTO.getProvince());
         address.setCountry(requestDTO.getCountry());
         address.setPostalCode(requestDTO.getPostalCode());

         client.setFirstName(requestDTO.getFirstName());
         client.setLastName(requestDTO.getLastName());
         client.setDni(requestDTO.getDni());
         client.setDocumentType(requestDTO.getDocumentType());
         client.setEmail(requestDTO.getEmail());
         client.getAddressList().add(address);

         addressRepository.save(address);
         clientRepository.save(client);
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
         return requestDTO;
     }*/

}
