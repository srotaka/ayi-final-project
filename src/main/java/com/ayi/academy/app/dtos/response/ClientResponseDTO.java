package com.ayi.academy.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ClientResponseDTO",description = "Information needed to create a Client")
public class ClientResponseDTO implements Serializable {

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

    @ApiModelProperty(notes = "Client type and points information", required = true, position = 7)
    private ClientDetailsResponseDTO clientDetailsId;

    @ApiModelProperty(notes = "Client address list information", required = true, position = 8)
    private List<AddressResponseDTO> addressList;

    @ApiModelProperty(notes = "Client bill list information", required = true, position = 9)
    private List<InvoiceResponseDTO> billList;
}
