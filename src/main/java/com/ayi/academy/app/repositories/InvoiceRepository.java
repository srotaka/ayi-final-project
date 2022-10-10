package com.ayi.academy.app.repositories;
import com.ayi.academy.app.dtos.response.InvoiceResponseDTO;
import com.ayi.academy.app.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(value = "SELECT invoices.desctiption, invoices.total_amount, clients.first_name, clients.last_name, clients.email FROM invoices , clients  WHERE invoices.client_id = clients.client_id", nativeQuery = true)
    Page<InvoiceResponseDTO> getInvoiceAndClientBasicInfo(Pageable pageable);

    @Query(value = "SELECT * FROM invoices WHERE client_id = :clientId", nativeQuery = true)
    List<Invoice> getAllInvoicesByIdClient(@Param("clientId") Integer clientId);
}
