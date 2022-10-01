package com.ayi.academy.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "BillResponseDTO",description = "Information needed to create a Bill")
public class BillResponseDTO implements Serializable {

    @NotNull
    @NotBlank(message = "ID is required")
    @ApiModelProperty(notes = "The unique id of a bill",  required = true, position = 1)
    private Integer id;

    @NotNull
    @NotBlank(message = "Description of the purchase is required")
    @ApiModelProperty(notes = "Description of the purchase",position = 2)
    private String description;

    @NotNull
    @NotBlank(message = "Total amount of the purchase is required")
    @DecimalMin(value = "0.1", message = "Amount must be greater than 0.1")
    @ApiModelProperty(notes = "Total amount of the purchase",position = 3)
    private Double totalAmount;
}
