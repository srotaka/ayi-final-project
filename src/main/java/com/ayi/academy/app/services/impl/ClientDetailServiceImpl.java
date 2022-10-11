package com.ayi.academy.app.services.impl;

import com.ayi.academy.app.dtos.response.ClientDetailsResponseDTO;
import com.ayi.academy.app.entities.ClientDetails;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.mappers.IClientDetailsMapper;
import com.ayi.academy.app.repositories.ClientDetailsRepository;
import com.ayi.academy.app.services.IClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ClientDetailServiceImpl implements IClientDetailsService {

    @Autowired
    ClientDetailsRepository detailsRepository;
    @Autowired
    IClientDetailsMapper detailsMapper;

    @Override
    public ClientDetailsResponseDTO getClientDetailsByClientId(Integer clientId)throws ReadAccessException {
        ClientDetails response =  detailsRepository.getClientDetailsByClientId(clientId);
        if(response == null){
            throw new ReadAccessException("No details registered for client ID " + clientId);
        }

            ClientDetailsResponseDTO responseDTO = detailsMapper.entityToDto(response);


        return responseDTO;
    }



}
