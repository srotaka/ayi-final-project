package com.ayi.academy.app.dtos.response;

import com.ayi.academy.app.entities.Client;
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
@ApiModel(value = "InvoiceResponseDTO",description = "Information needed to create an Invoice")
public class InvoiceResponseDTO implements Serializable {

    @NotNull
    @ApiModelProperty(notes = "The unique id of an invoice", position = 1)
    private Integer invoiceId;

    @NotNull
    @ApiModelProperty(notes = "Description of the purchase",position = 2)
    private String description;

    @NotNull
    @ApiModelProperty(notes = "Total amount of the purchase",position = 3)
    private Double totalAmount;

    @NotNull
    @JsonIgnore
    @ApiModelProperty(notes = "Client ID", required = true, position = 4, hidden = true)
    private Client clientId;
}
