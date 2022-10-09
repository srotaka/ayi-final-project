package com.ayi.academy.app.dtos.response;

import com.ayi.academy.app.entities.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AddressResponseDTO",description = "Information needed to register an Address")
public class AddressResponseDTO implements Serializable {

    @NotNull
    @ApiModelProperty(notes = "The unique id of an address",  position = 1)
    private Integer addressId;

    @NotNull
    @ApiModelProperty(notes = "Street name", example = "San Martín", required = true, position = 2)
    private String street;

    @NotNull
    @ApiModelProperty(notes = "Street number", example = "3364", required = true, position = 3)
    private Integer number;

    @ApiModelProperty(notes = "Address floor", example = "11",required = true, position = 4)
    private Integer floor;

    @ApiModelProperty(notes = "Address apartment unit", example = "B",position = 5)
    private String apartmentUnit;

    @NotNull
    @ApiModelProperty(notes = "City", example = "Rosario", required = true, position = 6)
    private String city;

    @NotNull
    @ApiModelProperty(notes = "Province", example = "Santa Fe", required = true, position = 7)
    private String province;

    @NotNull
    @ApiModelProperty(notes = "Country", example = "Argentina", required = true, position = 8)
    private String country;

    @NotNull
    @ApiModelProperty(notes = "Postal code", example = "2000", required = true, position = 9)
    private Integer postalCode;

    @NotNull
    @JsonIgnore
    @ApiModelProperty(notes = "Client ID", required = true, position = 10, hidden = true)
    private Client clientId;

}
