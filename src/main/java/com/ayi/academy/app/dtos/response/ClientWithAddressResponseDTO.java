package com.ayi.academy.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ClientResponseDTO",description = "Information needed to create a Client")
public class ClientWithAddressResponseDTO {

    @NotNull
    @ApiModelProperty(notes = "The unique id of a client",  required = true, position = 1)
    private Integer clientId;

    @NotNull
    @ApiModelProperty(notes = "Client's first name is required",  required = true, position = 2)
    private String firstName;

    @NotNull
    @ApiModelProperty(notes = "Client's last name is required", required = true, position = 3)
    private String lastName;

    @NotNull
    @Positive(message = "DNI cannot be a negative number")
    @Min(value = 10000000,message = "DNI must have at least 8 digits")
    @ApiModelProperty(notes = "Client's DNI", required = true, position = 4)
    private Long dni;

    @NotNull
    @ApiModelProperty(notes = "Client's DNI is required", required = true, position = 5)
    private String documentType;

    @Email(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email format must be valid")
    @ApiModelProperty(notes = "Client's email address is required", position = 6)
    private String email;

    @NotNull
    @ApiModelProperty(notes = "Street name is required", example = "San Martín", required = true, position = 7)
    private String street;

    @NotNull
    @Max(value = 999999,message = "Street number cannot have more than 6 digits")
    @Positive(message = "Street number cannot be a negative number")
    @ApiModelProperty(notes = "Street number is required", example = "3364", required = true, position = 8)
    private Integer number;

    @Max(value = 999999,message = "Address floor cannot have more than 6 digits")
    @Positive(message = "Floor number cannot be a negative number")
    @ApiModelProperty(notes = "Address floor", example = "11",required = true, position = 9)
    private Integer floor;

    @ApiModelProperty(notes = "Address apartment unit", example = "B",position = 5)
    private String apartmentUnit;

    @NotNull
    @ApiModelProperty(notes = "City is required", example = "Rosario", required = true, position = 10)
    private String city;

    @NotNull
    @ApiModelProperty(notes = "Province is required", example = "Santa Fe", required = true, position = 11)
    private String province;

    @NotNull
    @ApiModelProperty(notes = "Country is required", example = "Argentina", required = true, position = 12)
    private String country;

    @NotNull
    @ApiModelProperty(notes = "Address postal code is required", example = "2000", required = true, position = 13)
    private Integer postalCode;

}
