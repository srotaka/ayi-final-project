package com.ayi.academy.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "InvoiceResponseDTO",description = "Information needed to create a Invoice")
public class InvoiceResponseDTO implements Serializable {

    @ApiModelProperty(notes = "The unique id of a bill", position = 1)
    private Integer billId;

    @ApiModelProperty(notes = "Description of the purchase",position = 2)
    private String description;

    @ApiModelProperty(notes = "Total amount of the purchase",position = 3)
    private Double totalAmount;

    @JsonIgnoreProperties(value = "invoiceList")
    @ApiModelProperty(notes = "Client ID", position = 4)
    private ClientResponseDTO client;
}
