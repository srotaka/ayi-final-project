package com.ayi.academy.app.entities;

import com.ayi.academy.app.enums.Province;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name="address")
@SQLDelete(sql = "UPDATE address SET is_active = false WHERE id = ?")
@Where(clause = "is_active = false")
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
    @NotBlank(message = "Street name is required")
    private String street;

    @Column(name = "number", nullable = false, length = 6)
    @NotBlank(message = "Street number is required")
    @Max(value = 999999,message = "Street number cannot have more than 6 digits")
    @Positive(message = "Street number cannot be a negative number")
    private Integer number;

    @Column(name = "floor", length = 6)
    @Max(value = 999999,message = "Address floor cannot have more than 6 digits")
    @Positive(message = "Floor number cannot be a negative number")
    private Integer floor;

    @Column(name = "apartment_unit", length = 6)
    private String apartmentUnit;

    @Column(name = "city", nullable = false, length = 50)
    @NotBlank(message = "City is required")
    private String city;

    @Column(name = "province", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Province is required")
    private Province province;

    @Column(name = "country", nullable = false, length = 45)
    @NotBlank(message = "Country is required")
    private String country;

    @Column(name = "postal_code", length = 5)
    @NotBlank(message = "Postal code is required")
    @Min(value = 1000,message = "Postal code must have at least 4 digits")
    @Max(value = 99999,message = "Postal code cannot have more than 5 digits")
    private Integer postalCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client clientId;

    @Column(name = "is_active")
    private boolean isActive = Boolean.FALSE;


}
