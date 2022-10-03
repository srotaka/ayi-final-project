package com.ayi.academy.app.mappers;

import com.ayi.academy.app.dtos.request.AddressRequestDTO;
import com.ayi.academy.app.dtos.request.AddressRequestWithoutClientDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.AddressResponsePages;
import com.ayi.academy.app.entities.Address;

import java.util.List;

public interface IAddressMapper {
    AddressResponseDTO entityToDto(Address address);

    Address dtoToEntity(AddressRequestDTO requestDTO);

    Address convertDtoToEntityWithoutClient(AddressRequestWithoutClientDTO request);

    AddressResponsePages pagedAddressList(List<Address> addressList);
}
