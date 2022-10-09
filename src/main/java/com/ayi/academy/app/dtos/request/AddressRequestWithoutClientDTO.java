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

    @NotNull(message = "Street name")
    @ApiModelProperty(example = "San Martín", required = true, position = 1)
    private String street;

    @NotNull(message = "Street number")
    @ApiModelProperty(example = "3364", required = true, position = 2)
    private Integer number;

    @ApiModelProperty(notes = "Address floor", example = "11",required = true, position = 3)
    private Integer floor;

    @ApiModelProperty(notes = "Address apartment unit", example = "B",position = 4)
    private String apartmentUnit;

    @NotNull(message = "City")
    @ApiModelProperty(example = "Rosario", required = true, position = 5)
    private String city;

    @NotNull(message = "Province")
    @ApiModelProperty(example = "Santa Fe", required = true, position = 6)
    private String province;

    @NotNull(message = "Country")
    @ApiModelProperty(example = "Argentina", required = true, position = 7)
    private String country;

    @NotNull(message = "Postal code")
    @ApiModelProperty(example = "2000", required = true, position = 8)
    private Integer postalCode;


}
