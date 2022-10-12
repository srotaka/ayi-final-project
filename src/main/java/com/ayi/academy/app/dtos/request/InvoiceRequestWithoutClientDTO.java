package com.ayi.academy.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "InvoiceRequestWithoutClientDTO",description = "Information needed to create an Invoice")
public class InvoiceRequestWithoutClientDTO {

    @NotNull(message = "The description of purchase")
    @ApiModelProperty(example = "Monopatín", position = 1)
    private String description;

    @NotNull(message = "Total amount of purchase")
    @ApiModelProperty(example = "350",position = 2)
    private Double totalAmount;
}
