package com.ayi.academy.app.services.impl;

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
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.Field;

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
                )).collect(Collectors.toList());
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

        if(id == null || id <= 0){
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
    public AddressResponsePages getPagedAddresses(Integer page, Integer size) throws ReadAccessException {

        AddressResponsePages addressResponsePages;
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addressPage = addressRepository.findAll(pageable);

        if(addressPage != null && !addressPage.isEmpty()) {
            addressResponsePages = addressMapper.pagedAddressList(addressPage.getContent());
            addressResponsePages.setAddressesPerPage(addressPage.getSize());
            addressResponsePages.setCurrentPage(addressPage.getNumber() + 1);
            addressResponsePages.setTotalPages(addressPage.getTotalPages());
            addressResponsePages.setTotalAddresses((int) addressPage.getTotalElements());
            return addressResponsePages;
        } else {
            throw new ReadAccessException("Error paginating address information");
        }
    }

    @Override
    public AddressResponseDTO  updateAddress(Integer id, Map<String, Object> fields) throws ReadAccessException {
        if(id == null || id < 0){
            throw new ReadAccessException("ID is required");
        }
        AddressResponseDTO addressResponseDTO;
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (!addressOptional.isPresent()) {
            throw new ReadAccessException("No address found with that id");
        }

        try {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Address.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, addressOptional.get(), value);
            });
            Address updatedAddress = addressRepository.save(addressOptional.get());
            return addressMapper.entityToDto(updatedAddress);
        }catch (Exception e) {
            throw new ReadAccessException("ID is required");
        }
    }

}


