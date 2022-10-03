package com.ayi.academy.app.dtos.request;

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
@ApiModel(value = "AddressRequestWithoutClientDTO",description = "Information needed to register an Address without Client")
public class AddressRequestWithoutClientDTO implements Serializable {

    @NotNull
    @ApiModelProperty(notes = "Street name is required", example = "San Martín", required = true, position = 1)
    private String street;

    @NotNull
    @Max(value = 999999,message = "Street number cannot have more than 6 digits")
    @Positive(message = "Street number cannot be a negative number")
    @ApiModelProperty(notes = "Street number is required", example = "3364", required = true, position = 2)
    private Integer number;

    @Max(value = 999999,message = "Address floor cannot have more than 6 digits")
    @Positive(message = "Floor number cannot be a negative number")
    @ApiModelProperty(notes = "Address floor", example = "11",required = true, position = 3)
    private Integer floor;

    @ApiModelProperty(notes = "Address apartment unit", example = "B",position = 4)
    private String apartmentUnit;

    @NotNull
    @ApiModelProperty(notes = "City is required", example = "Rosario", required = true, position = 5)
    private String city;

    @NotNull
    @ApiModelProperty(notes = "Province is required", example = "Santa Fe", required = true, position = 6)
    private String province;

    @NotNull
    @ApiModelProperty(notes = "Country is required", example = "Argentina", required = true, position = 7)
    private String country;

    @NotNull
    @Min(value = 1000,message = "Postal code must have at least 4 digits")
    @Max(value = 99999,message = "Postal code cannot have more than 5 digits")
    @ApiModelProperty(notes = "Address postal code is required", example = "2000", required = true, position = 8)
    private Integer postalCode;

}
