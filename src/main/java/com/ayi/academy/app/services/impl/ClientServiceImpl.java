package com.ayi.academy.app.services.impl;

import com.ayi.academy.app.dtos.request.ClientRequestDTO;
import com.ayi.academy.app.dtos.response.*;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.entities.Client;
import com.ayi.academy.app.entities.ClientDetails;
import com.ayi.academy.app.entities.Invoice;
import com.ayi.academy.app.exceptions.ReadAccessException;
import com.ayi.academy.app.mappers.IAddressMapper;
import com.ayi.academy.app.mappers.IClientDetailsMapper;
import com.ayi.academy.app.mappers.IClientMapper;
import com.ayi.academy.app.mappers.IInvoiceMapper;
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
    private IClientDetailsMapper detailsMapper;
    @Autowired
    private IAddressMapper addressMapper;
    @Autowired
    private IInvoiceMapper invoiceMapper;

    /**
     * This method retrieves a list of all clients registered in database.
     * @throws ReadAccessException
     */
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
    /**
     * This method retrieves a client by its ID.
     * @param id
     * @throws ReadAccessException
     */
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

    /**
     * This method deletes a registered client.
     * @param id
     * @throws ReadAccessException
     */
    @Override
    public void deleteClient(Integer id) throws ReadAccessException {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new ReadAccessException("Error. ID not found.");
        }
    }

    /**
     * This method retrieves a list of all paginated clients.
     * @param page
     * @param size
     * @throws ReadAccessException
     */
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

    /**
     * This method allows to register a new client.
     * DNI and email must be uniques.
     * @param request
     * @throws ReadAccessException
     */
    @Override
    public ClientResponseDTO createClient(ClientRequestDTO request) throws ReadAccessException {

        Client client = clientMapper.dtoToEntity(request);
        Optional<Client> clientDni = Optional.ofNullable(clientRepository.findByDni(client.getDni()));
        if(clientDni.isPresent()){
            throw new ReadAccessException("DNI already registered.");
        }
        Optional<Client> clientEmail = Optional.ofNullable(clientRepository.findByEmail(client.getEmail()));
        if(clientEmail.isPresent()){
            throw new ReadAccessException("Email already registered.");
        }

        ClientDetails details = detailsMapper.dtoToEntity(request.getClientDetailsId());
        Address address = addressMapper.dtoToEntity(request.getAddressList().get(0));
        Invoice invoice = invoiceMapper.dtoToEntity(request.getInvoiceList().get(0));

        details.setClientId(client);
        client.setClientDetailsId(details);
        clientRepository.save(client);

        return clientMapper.entityToDto(client);
    }
    /**
     * This method allows to update the necessary information of a client.
     * No need to enter all client info. Only the ones needed.
     * @param id
     * @param fields
     * @throws ReadAccessException
     */
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
