package com.ayi.academy.app.services.impl;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.response.*;
import com.ayi.academy.app.entities.Client;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.mappers.IClientMapper;
import com.ayi.academy.app.repositories.ClientDetailsRepository;
import com.ayi.academy.app.repositories.ClientRepository;
import com.ayi.academy.app.services.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ClientServiceImpl implements IClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private IClientMapper clientMapper;
    @Autowired
    ClientDetailsRepository detailsRepository;

    @Override
    public List<ClientResponseDTO> getAllClients() throws ReadAccessException {

        List<Client> clientList = clientRepository.findAll();


        if (clientList == null || clientList.size() == 0) {
            throw new ReadAccessException("No clients registered.");
        }

        List<ClientResponseDTO> responseDTOList = new ArrayList<>();

        clientList.forEach(client -> {
            ClientResponseDTO clientResponse = clientMapper.entityToDto(client);
            responseDTOList.add(clientResponse);
        });


        return responseDTOList;
    }

    @Override
    public ClientResponseDTO findClientById(Integer id) throws ReadAccessException {
        if (id == null || id <= 0) {
            throw new ReadAccessException("Valid ID is required");
        }

        ClientResponseDTO responseDTO = new ClientResponseDTO();
        Client client = clientRepository.findById(id).get();

        if (client == null || client.getClientId() <= 0) {
            throw new ReadAccessException("No client registered with ID " + id);
        }
        responseDTO = clientMapper.entityToDto(client);

        return responseDTO;
    }

    @Override
    public void deleteClient(Integer id) throws ReadAccessException {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new ReadAccessException("Error. ID not found.");
        }
    }

    @Override
    public ClientResponsePages getPagedClients(Integer page, Integer size) throws ReadAccessException {

        ClientResponsePages clientResponsePages;
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clientPage = clientRepository.findAll(pageable);

        if (clientPage != null && !clientPage.isEmpty()) {
            clientResponsePages = clientMapper.pagedClientList(clientPage.getContent());
            clientResponsePages.setClientsPerPage(clientPage.getSize());
            clientResponsePages.setCurrentPage(clientPage.getNumber() + 1);
            clientResponsePages.setTotalPages(clientPage.getTotalPages());
            clientResponsePages.setTotalClients((int) clientPage.getTotalElements());
            return clientResponsePages;
        } else {
            throw new ReadAccessException("Error paginating client information");
        }
    }


    @Override
    public ClientResponseDTO createClient(ClientRequestDTO request) {
        Client client = clientMapper.dtoToEntity(request);
        clientRepository.save(client);
        //detailsRepository.save(client.getClientDetailsId());


        return clientMapper.entityToDto(client);
    }
    @Override
    public ClientWithDetailsResponseDTO updateClient(Integer id, Map<String, Object> fields) throws ReadAccessException {
        if(id == null || id < 0){
            throw new ReadAccessException("ID is required (first validation)");
        }
        ClientWithDetailsResponseDTO clientDetailsResponseDTO;
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (!clientOptional.isPresent()) {
            throw new ReadAccessException("No client found with that id");
        }

        try {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Client.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, clientOptional.get(), value);
                System.out.println();
            });
            Client updatedClient = clientRepository.save(clientOptional.get());

            return clientMapper.clientWithDetailsEntityToDto(updatedClient);
        }catch (Exception e) {
            throw new ReadAccessException("ID is required last catch");
        }
    }


}
