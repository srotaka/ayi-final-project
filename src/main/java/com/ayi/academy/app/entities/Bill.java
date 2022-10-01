package com.ayi.academy.app.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="bills")
@SQLDelete(sql = "UPDATE bills SET is_active = false WHERE id = ?")
@Where(clause = "is_active = false")
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
    @NotBlank(message = "Description of the purchase is required")
    private String description;

    @Column(name = "total_amount", nullable = false, length = 8)
    @NotBlank(message = "Total amount of the purchase is required")
    @DecimalMin(value = "0.1", message = "Amount must be greater than 0.1")
    private Double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client clientId;

    @Column(name = "is_active")
    private boolean isActive = Boolean.FALSE;

}
