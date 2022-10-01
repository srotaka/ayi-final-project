package com.ayi.academy.app.dtos.response;

import com.ayi.academy.app.dtos.request.AddressRequestDTO;
import com.ayi.academy.app.dtos.request.BillRequestDTO;
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
    @NotBlank(message = "ID is required")
    @ApiModelProperty(notes = "The unique id of a client",  required = true, position = 1)
    private Integer clientId;

    @NotNull
    @NotBlank(message = "First Name is required")
    @ApiModelProperty(notes = "Client's first name",  required = true, position = 2)
    private String firstName;

    @NotNull
    @NotBlank(message = "Last Name is required")
    @ApiModelProperty(notes = "Client's last name", required = true, position = 3)
    private String lastName;

    @NotNull
    @NotBlank(message = "DNI is required")
    @Positive(message = "DNI cannot be a negative number")
    @Min(value = 10000000,message = "DNI must have at least 8 digits")
    @ApiModelProperty(notes = "Client's DNI", required = true, position = 4)
    private Long dni;

    @NotNull
    @NotBlank(message = "DNI Type is required")
    @ApiModelProperty(notes = "Client's DNI", required = true, position = 5)
    private String documentType;

    @Email(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email format must be valid")
    @ApiModelProperty(notes = "Client's email address", position = 6)
    private String email;

    @NotNull
    @NotNull(message = "Client detail is required.")
    @ApiModelProperty(notes = "Client type and points information", required = true, position = 7)
    private ClientDetailsResponseDTO clientDetailsId;

    @NotNull(message = "Client address list is required.")
    @ApiModelProperty(notes = "Client address list information", required = true, position = 8)
    private List<AddressResponseDTO> addressList;

    @NotNull(message = "Client bill list is required.")
    @ApiModelProperty(notes = "Client bill list information", required = true, position = 9)
    private List<BillResponseDTO> billList;
}
