package com.ayi.academy.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ClientResponseDTO",description = "Information needed to create a Client")
public class ClientBasicResponseDTO implements Serializable {
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

}
