package com.ayi.academy.app.dtos.request;

import com.ayi.academy.app.enums.ClientType;
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
@ApiModel(value = "ClientDetailsRequestDTO",description = "Information needed to create Client Detail information")
public class ClientDetailsRequestDTO implements Serializable {

    @NotNull
    @Positive(message = "Points cannot be lower than 0")
    @ApiModelProperty(notes = "Client's purchases points is required", required = true, position = 1)
    private Long points;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Client type is required", required = true, position = 2)
    private ClientType clientType;

       @ApiModelProperty(notes = "Client", required = true, position = 3)
    private ClientRequestDTO client;

}
