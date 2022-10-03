package com.ayi.academy.app.repositories;
import com.ayi.academy.app.dtos.response.AddressResponseDTO;
import com.ayi.academy.app.entities.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "SELECT address.street, address.number, address.floor, address.apartment_unit, address.postal_code, address.city, address.province, address.country, clients.first_name, clients.last_name, clients.email FROM address , clients  WHERE address.client_id = clients.client_id", nativeQuery = true)
    Page<AddressResponseDTO> getAddressAndClientBasicInfo(Pageable pageable);

    @Query(value = "SELECT * FROM address WHERE client_id = :clientId", nativeQuery = true)
    List<Address> getAllAddressByIdClient(@Param("clientId") Integer clientId);
}
