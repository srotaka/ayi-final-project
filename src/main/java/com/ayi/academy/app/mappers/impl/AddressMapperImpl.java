package com.ayi.academy.app.mappers.impl;
import com.ayi.academy.app.dtos.request.AddressRequestDTO;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.mappers.IAddressMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapperImpl implements IAddressMapper {
    private final ModelMapper modelMapper;

    @Override
    public AddressResponseDTO entityToDto(Address entity){

        AddressResponseDTO responseDTO = new AddressResponseDTO();
        modelMapper.map(entity, responseDTO);
        return responseDTO;
    }

    @Override
    public Address dtoToEntity(AddressRequestDTO requestDTO){

        Address entity = new Address();
        modelMapper.map(requestDTO, entity);
        return  entity;
    }


}
