package com.ayi.academy.app.entities;

import lombok.*;;
import javax.persistence.*;

@Entity
@Table(name="address")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Column(name = "number", nullable = false, length = 6)
    private Integer number;

    @Column(name = "floor", length = 6)
    private Integer floor;

    @Column(name = "apartment_unit", length = 6)
    private String apartmentUnit;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "province", nullable = false, length = 20)
    private String province;

    @Column(name = "country", nullable = false, length = 45)
    private String country;

    @Column(name = "postal_code", length = 5)
    private Integer postalCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client clientId;

}
