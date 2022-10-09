package com.ayi.academy.app.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel(value = "Invoice RequestDTO",description = "Information needed to create an Invoice")
public class InvoiceRequestDTO implements Serializable {

    @NotNull(message = "The description of purchase")
    @ApiModelProperty(example = "Monopatín", position = 1)
    private String description;

    @NotNull(message = "Total amount of purchase")
    @ApiModelProperty(example = "350",position = 2)
    private Double totalAmount;

    @NotNull(message = "Client ID")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private ClientRequestDTO clientId;
}
