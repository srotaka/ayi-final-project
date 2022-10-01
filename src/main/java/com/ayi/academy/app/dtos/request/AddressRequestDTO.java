package com.ayi.academy.app.dtos.request;

import com.ayi.academy.app.enums.Province;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AddressRequestDTO",description = "Information needed to register an Address")
public class AddressRequestDTO implements Serializable {

    @NotNull
    @NotBlank(message = "Street name is required")
    @ApiModelProperty(notes = "Street name", required = true, position = 1)
    private String street;

    @NotNull
    @NotBlank(message = "Street number is required")
    @Max(value = 999999,message = "Street number cannot have more than 6 digits")
    @Positive(message = "Street number cannot be a negative number")
    @ApiModelProperty(notes = "Street number", required = true, position = 2)
    private Integer number;

    @NotNull
    @Max(value = 999999,message = "Address floor cannot have more than 6 digits")
    @Positive(message = "Floor number cannot be a negative number")
    @ApiModelProperty(notes = "Address floor", required = true, position = 3)
    private Integer floor;

    @ApiModelProperty(notes = "Address apartment unit",position = 4)
    private String apartmentUnit;

    @NotNull
    @NotBlank(message = "City is required")
    @ApiModelProperty(notes = "City", required = true, position = 5)
    private String city;

    @NotNull
    @NotBlank(message = "Province is required")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Province", required = true, position = 6)
    private Province province;

    @NotNull
    @NotBlank(message = "Country is required")
    @ApiModelProperty(notes = "Country", required = true, position = 7)
    private String country;

    @NotNull
    @NotBlank(message = "Postal code is required")
    @Min(value = 1000,message = "Postal code must have at least 4 digits")
    @Max(value = 99999,message = "Postal code cannot have more than 5 digits")
    @ApiModelProperty(notes = "Address postal code", required = true, position = 8)
    private Integer postalCode;

    @NotNull
    @NotBlank(message = "Client information is required")
    @ApiModelProperty(notes = "Client", required = true, position = 9)
    private ClientRequestDTO client;
}
