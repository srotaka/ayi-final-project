package com.ayi.academy.app.dtos.response;

import com.ayi.academy.app.enums.ClientType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "ID is required")
    @ApiModelProperty(notes = "The unique id of a client",  required = true, position = 1)
    private Integer id;

    @NotNull
    @NotBlank(message = "Points are required")
    @Positive(message = "Points cannot be lower than 0")
    @ApiModelProperty(notes = "Client's purchases points", required = true, position = 2)
    private Long points;

    @NotNull
    @NotBlank(message = "Client Type is required")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Client type", required = true, position = 3)
    private ClientType clientType;
}
