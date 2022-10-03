package com.ayi.academy.app.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="bills")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "total_amount", nullable = false, length = 8)
    @DecimalMin(value = "0.1", message = "Amount must be greater than 0.1")
    private Double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client clientId;
}
