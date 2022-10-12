package com.ayi.academy.app.services;

import com.ayi.academy.app.dtos.response.ClientDetailsResponseDTO;
import com.ayi.academy.app.exceptions.ReadAccessException;

import java.util.List;

public interface IClientDetailsService {
    ClientDetailsResponseDTO getClientDetailsByClientId(Integer clientId)throws ReadAccessException;
}
