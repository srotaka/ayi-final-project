package com.ayi.academy.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Client Details ResponseDTO",description = "Information needed to create Client Detail information")
public class ClientDetailsResponseDTO implements Serializable {

    @ApiModelProperty(notes = "The unique id of a client detail",  position = 1)
    private Integer clientDetailId;

    @ApiModelProperty(notes = "Client's purchases points", position = 2)
    private Long points;

    @ApiModelProperty(notes = "Client status", position = 3)
    private Boolean isVIP;

    @JsonIgnore
    @ApiModelProperty(notes = "Client", position = 4, hidden = true)
    private ClientResponseDTO client;
}
