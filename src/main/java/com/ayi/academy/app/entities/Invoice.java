package com.ayi.academy.app.entities;

import lombok.*;
import javax.persistence.*;
@Entity
@Table(name="invoices")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "total_amount", nullable = false, length = 8)
    private Double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client", referencedColumnName = "client_id")
    private Client client;
}
