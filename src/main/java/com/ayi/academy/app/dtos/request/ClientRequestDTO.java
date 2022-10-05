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
@ApiModel(value = "ClientRequestDTO",description = "Information needed to create a Client")
public class ClientRequestDTO implements Serializable {

    @NotNull
    @ApiModelProperty(notes = "Client's first name is required", example = "Tomás", required = true, position = 1)
    private String firstName;

    @NotNull
    @ApiModelProperty(notes = "Client's last name is required", example = "Black", required = true, position = 2)
    private String lastName;

    @NotNull
    @Positive(message = "DNI cannot be a negative number")
    @Min(value = 10000000,message = "DNI must have at least 8 digits")
    @ApiModelProperty(notes = "Client's DNI is required", example = "45632951", required = true, position = 3)
    private Long dni;

    @NotNull
    @ApiModelProperty(notes = "Client's DNI type is required", example = "DNI", required = true, position = 4)
    private String documentType;

    @Email(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email format must be valid")
    @ApiModelProperty(notes = "Client's email address", example = "tomas@mail.com",  position = 5)
    private String email;

    @ApiModelProperty(notes = "Client type and points information", required = true, position = 6)
    private ClientDetailsRequestDTO clientDetailsId;

    @ApiModelProperty(notes = "Client address list information", required = true, position = 7)
    private AddressRequestDTO addressRequest;

    @ApiModelProperty(notes = "Client bill list information", required = true, position = 8)
    private InvoiceRequestDTO billRequest;
}
