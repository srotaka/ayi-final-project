package com.ayi.academy.app.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@SQLDelete(sql = "UPDATE clients SET is_active = true WHERE id=?")
@Where(clause = "is_active = false")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "First Name is required")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Column(name = "dni", nullable = false, length = 15)
    @NotBlank(message = "DNI is required")
    @Positive(message = "DNI cannot be a negative number")
    @Min(value = 10000000, message = "DNI must have at least 8 digits")
    private Long dni;

    @Column(name = "document_type", nullable = false, length = 9)
    @NotBlank(message = "DNI Type is required")
    private String documentType;

    @Column(name = "email", unique = true, length = 50)
    @Email(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Mail format must be valid")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_detail_id", referencedColumnName = "client_detail_id")
    private ClientDetails clientDetailsId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addressList = new ArrayList<>();;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bill> billList = new ArrayList<>();;

    @Column(name = "is_active")
    private boolean isActive = Boolean.FALSE;


}
