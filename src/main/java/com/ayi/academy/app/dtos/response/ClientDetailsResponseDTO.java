package com.ayi.academy.app.dtos.response;

import com.ayi.academy.app.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ClientDetailsResponseDTO",description = "Information needed to create Client Detail information")
public class ClientDetailsResponseDTO implements Serializable {

    @NotNull
    @ApiModelProperty(notes = "The unique id of a client",  required = true, position = 1)
    private Integer clientDetailId;

    @NotNull
    @Positive(message = "Points cannot be lower than 0")
    @ApiModelProperty(notes = "Client's purchases points is required", required = true, position = 2)
    private Long points;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Client type is required", required = true, position = 3)
    private ClientType clientType;


    @JsonIgnore
    @ApiModelProperty(notes = "Client", required = true, position = 4)
    private ClientResponseDTO client;
}
