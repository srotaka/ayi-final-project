package com.ayi.academy.app.repositories;
import com.ayi.academy.app.entities.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Integer> {
}
