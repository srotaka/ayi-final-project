package com.ayi.academy.app.mappers;

import com.ayi.academy.app.dtos.request.AddressRequestDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.entities.Address;

public interface IAddressMapper {
    AddressResponseDTO entityToDto(Address address);

    Address dtoToEntity(AddressRequestDTO requestDTO);
}
