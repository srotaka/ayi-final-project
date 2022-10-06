package com.ayi.academy.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ClientDetailsRequestDTO",description = "Information needed to create Client Detail information")
public class ClientDetailsRequestDTO implements Serializable {

    @ApiModelProperty(notes = "Client's purchases points", example = "500", position = 1)
    private Long points;

    @NotNull(message = "VIP status are required")
    @ApiModelProperty(notes = "Client status is required", example = "1", position = 2)
    private Boolean isVIP;


}
