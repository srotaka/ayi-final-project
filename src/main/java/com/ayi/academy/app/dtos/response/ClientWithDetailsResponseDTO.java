package com.ayi.academy.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ClientResponseDTO",description = "Client with detail information")
public class ClientWithDetailsResponseDTO {

    @ApiModelProperty(notes = "The unique id of a client",  position = 1)
    private Integer clientId;

    @ApiModelProperty(notes = "Client's first name", position = 2)
    private String firstName;

    @ApiModelProperty(notes = "Client's last name", position = 3)
    private String lastName;

    @ApiModelProperty(notes = "Client's DNI", position = 4)
    private Long dni;

    @ApiModelProperty(notes = "Client's DNI", position = 5)
    private String documentType;

    @ApiModelProperty(notes = "Client's email address", position = 6)
    private String email;

    @ApiModelProperty(notes = "Client type and points information", position = 7)
    private ClientDetailsResponseDTO clientDetailsId;

    @ApiModelProperty(notes = "Client address list information", position = 8)
    private List<AddressResponseDTO> addressList;

}
