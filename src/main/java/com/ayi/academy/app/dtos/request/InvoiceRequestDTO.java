package com.ayi.academy.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Invoice RequestDTO",description = "Information needed to create an Invoice")
public class InvoiceRequestDTO implements Serializable {

    @NotNull(message = "The description cannot be null")
    @ApiModelProperty(notes = "Description of the purchase is required",position = 1)
    private String description;

    @NotNull(message = "Total amount cannot be null")
    @DecimalMin(value = "0.1", message = "Amount must be greater than 0.1")
    @ApiModelProperty(notes = "Total amount of the purchase is required",position = 2)
    private Double totalAmount;

    @NotNull(message = "The Client cannot be null")
    @ApiModelProperty(notes = "Client ID", position = 3)
    private ClientRequestDTO client;
}
