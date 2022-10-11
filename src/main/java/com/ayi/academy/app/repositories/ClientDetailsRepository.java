package com.ayi.academy.app.repositories;
import com.ayi.academy.app.entities.Address;
import com.ayi.academy.app.entities.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Integer> {
    @Query(value = "SELECT * FROM client_details WHERE client_id = :clientId", nativeQuery = true)
    ClientDetails getClientDetailsByClientId(@Param("clientId") Integer clientId);

}
