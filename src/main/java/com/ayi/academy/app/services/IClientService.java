package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.response.ClientResponseDTO;

public interface IClientService {

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
    ClientResponseDTO createClient(ClientRequestDTO requestDTO);
}
