package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.request.AddressRequestDTO;
import com.ayi.academy.app.dtos.request.AddressRequestWithoutClientDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.AddressResponsePages;
import com.ayi.academy.app.exceptions.ReadAccessException;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IAddressService {
    @Transactional
    AddressResponseDTO createAddress (AddressRequestWithoutClientDTO requestDTO, Integer clientId);

    @Transactional
    List<AddressResponseDTO> getAllAddressWithoutClient() throws ReadAccessException;

    @org.springframework.transaction.annotation.Transactional
    List<AddressResponseDTO> getAllAddressByClientId(Integer clientId)throws ReadAccessException;

    AddressResponseDTO findAddressById(Integer id) throws ReadAccessException;

    void deleteAddress(Integer id) throws ReadAccessException;

    AddressResponsePages getPagedAddresses(Integer page, Integer size) throws ReadAccessException;

   /* ResponseEntity<?> updateAddress(Integer id, Map<Object, Object> fields) throws ReadAccessException;*/

    AddressResponseDTO updateAddress(Integer id, AddressRequestDTO requestDTO);
}
