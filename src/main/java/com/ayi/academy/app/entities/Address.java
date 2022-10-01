package com.ayi.academy.app.entities;

import com.ayi.academy.app.enums.Province;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "The unique id of an address")
    private Integer id;

    @Column(name = "street", nullable = false, length = 50)
    @NotBlank(message = "Street name is required")
    @ApiModelProperty(notes = "Street name",position = 1)
    private String street;

    @Column(name = "number", nullable = false, length = 6)
    @NotBlank(message = "Street number is required")
    @Max(value = 999999,message = "Street number cannot have more than 6 digits")
    @Positive(message = "Street number cannot be a negative number")
    @ApiModelProperty(notes = "Street number",position = 2)
    private Integer number;

    @Column(name = "floor", length = 6)
    @Max(value = 999999,message = "Address floor cannot have more than 6 digits")
    @Positive(message = "Floor number cannot be a negative number")
    @ApiModelProperty(notes = "Address floor",position = 3)
    private Integer floor;

    @Column(name = "apartment_unit", length = 6)
    @ApiModelProperty(notes = "Address apartment unit",position = 4)
    private String apartmentUnit;

    @Column(name = "city", nullable = false, length = 50)
    @NotBlank(message = "City is required")
    @ApiModelProperty(notes = "City",position = 5)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "province", nullable = false, length = 20)
    @NotBlank(message = "Province is required")
    @ApiModelProperty(notes = "Province",position = 6)
    private Province province;

    @Column(name = "country", nullable = false, length = 45)
    @NotBlank(message = "Country is required")
    @ApiModelProperty(notes = "Country",position = 7)
    private String country;

    @Column(name = "postal_code", length = 5)
    @NotBlank(message = "Postal code is required")
    @Min(value = 1000,message = "Postal code must have at least 4 digits")
    @Max(value = 99999,message = "Postal code cannot have more than 5 digits")
    @ApiModelProperty(notes = "Address postal code",position = 8)
    private Integer postalCode;


    @Column(name = "is_active")
    @ApiModelProperty(notes = "Address logic delete flag",position = 9)
    private boolean isActive = Boolean.FALSE;


}
