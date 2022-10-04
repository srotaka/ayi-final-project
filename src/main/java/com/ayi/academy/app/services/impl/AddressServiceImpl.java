package com.ayi.academy.app.services.impl;

import com.ayi.academy.app.dtos.request.AddressRequestDTO;
import com.ayi.academy.app.dtos.request.AddressRequestWithoutClientDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.AddressResponsePages;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.entities.Client;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.mappers.IAddressMapper;
import com.ayi.academy.app.mappers.IClientMapper;
import com.ayi.academy.app.repositories.AddressRepository;
import com.ayi.academy.app.repositories.ClientRepository;
import com.ayi.academy.app.services.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private IAddressMapper addressMapper;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private IClientMapper clientMapper;

    @Override
    public AddressResponseDTO createAddress (AddressRequestWithoutClientDTO addressRequest, Integer clientId){
        Address address = addressMapper.convertDtoToEntityWithoutClient(addressRequest);
        Client client = clientRepository.findById(clientId).get();

        address.setClientId(client);
        address = addressRepository.save(address);
        return addressMapper.entityToDto(address);
    }

    @Override
    public List<AddressResponseDTO> getAllAddressWithoutClient() throws ReadAccessException {

        List<AddressResponseDTO> responseDTOList = null;
        List<Address> addressList = addressRepository.findAll();

        if(addressList == null || addressList.size() == 0){
            throw new ReadAccessException("No addresses registered.");
        }

        responseDTOList = addressList.stream()
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
                ))
                .collect(Collectors.toList());
        return responseDTOList;
    }

    @Override
    public List<AddressResponseDTO> getAllAddressByClientId(Integer clientId)throws ReadAccessException{
        List<AddressResponseDTO> responseDTOList = null;
        List<Address> addressList = addressRepository.getAllAddressByIdClient(clientId);
        if(addressList == null || addressList.size() == 0){
            throw new ReadAccessException("No addresses registered for client ID " + clientId);
        }

        responseDTOList = addressList.stream()
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
                ))
                .collect(Collectors.toList());
        return responseDTOList;
    }

    @Override
    public AddressResponseDTO findAddressById(Integer id) throws ReadAccessException {

        if(id == null || id < 0){
            throw new ReadAccessException("ID is required");
        }

        AddressResponseDTO addressResponseDTO;
        Optional<Address> address = addressRepository.findById(id);

        if (!address.isPresent()) {
            throw new ReadAccessException("Error. ID not found.");
        }

        return addressResponseDTO = addressMapper.entityToDto(address.get());
    }
    @Override
    public void deleteAddress(Integer id) throws ReadAccessException {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.deleteById(id);
        } else {
            throw new ReadAccessException("Error. ID not found.");
        }
    }

    @Override
    public AddressResponsePages getPagedAddresses(Integer page, Integer size) throws ReadAccessException { // Paginación

        AddressResponsePages addressResponsePages;
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addressPage = addressRepository.findAll(pageable);

        if(addressPage != null && !addressPage.isEmpty()) {
            addressResponsePages = addressMapper.pagedAddressList(addressPage.getContent());
            addressResponsePages.setItemsPerPage(addressPage.getSize());
            addressResponsePages.setCurrentPage(addressPage.getNumber() + 1);
            addressResponsePages.setTotalPages(addressPage.getTotalPages());
            addressResponsePages.setTotalElements((int) addressPage.getTotalElements());
            return addressResponsePages;
        } else {
            throw new ReadAccessException("Error paginating address information");
        }
    }
    @Override
    public AddressResponseDTO updateAddress(Integer id, AddressRequestDTO requestDTO) {
        Optional<Address> entityOptional = addressRepository.findById(id);
        Address entity = entityOptional.get();

        if(entityOptional.isPresent()) {
            entity.setStreet(requestDTO.getStreet());
            entity.setNumber(requestDTO.getNumber());
            entity.setFloor(requestDTO.getFloor());
            entity.setApartmentUnit(requestDTO.getApartmentUnit());
            entity.setCity(requestDTO.getCity());
            entity.setProvince(requestDTO.getProvince());
            entity.setCountry(requestDTO.getCountry());
            entity.setPostalCode(requestDTO.getPostalCode());
            entity.setClientId(clientMapper.dtoToEntity(requestDTO.getClientId()));

            addressRepository.save(entity);

            return addressMapper.entityToDto(entity);
        } else {
            throw new RuntimeException("No se encuentra el ID a actualizar");
        }
    }

    /*@Override
    public ResponseEntity<?> updateAddress(Integer id, Map<Object, Object> fields) throws ReadAccessException {

        if(id == null || id < 0){
            throw new ReadAccessException("ID is required");
        }
        AddressResponseDTO addressResponseDTO;
        Optional<Address> address = addressRepository.findById(id);

        if (!address.isPresent()) {
            throw new ReadAccessException("Error. ID not found.");
        }
        try {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Address.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, address.get(), value);
            });

            Address updatedAddress = addressRepository.save(address.get());

            return new ResponseEntity<Address>(updatedAddress, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

     */



}


