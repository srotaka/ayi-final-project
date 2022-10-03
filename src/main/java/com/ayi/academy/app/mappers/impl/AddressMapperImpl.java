package com.ayi.academy.app.mappers.impl;
import com.ayi.academy.app.dtos.request.AddressRequestDTO;
import com.ayi.academy.app.dtos.request.AddressRequestWithoutClientDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.dtos.response.AddressResponsePages;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.mappers.IAddressMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AddressMapperImpl implements IAddressMapper {
    private final ModelMapper modelMapper;

    @Override
    public AddressResponseDTO entityToDto(Address entity){

        return modelMapper.map(entity, AddressResponseDTO.class);
    }
    @Override
    public Address dtoToEntity(AddressRequestDTO request) {
        return modelMapper.map(request, Address.class);
    }
    @Override
    public Address convertDtoToEntityWithoutClient(AddressRequestWithoutClientDTO request) {
        return modelMapper.map(request, Address.class);
    }

    @Override
    public AddressResponsePages pagedAddressList(List<Address> addressList) {

        AddressResponsePages addressResponsePages = new AddressResponsePages();
        List<AddressResponseDTO> addressResponseList = new ArrayList<>();
        addressList.forEach((Address address) -> {
            AddressResponseDTO addressResponse = new AddressResponseDTO(
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
            );
            addressResponseList.add(addressResponse);
        });
        addressResponsePages.setAddressResponseList(addressResponseList);

        return addressResponsePages;
    }

}
