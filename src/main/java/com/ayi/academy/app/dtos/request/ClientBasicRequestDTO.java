package com.ayi.academy.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ClientRequestDTO",description = "Client with addresses, invoices and details information")
public class ClientBasicRequestDTO implements Serializable {
    @NotNull(message="Client's first name is required")
    @ApiModelProperty(example = "Tomás", required = true, position = 1)
    private String firstName;

    @NotNull(message = "Client's last name is required")
    @ApiModelProperty(example = "Black", required = true, position = 2)
    private String lastName;

    @NotNull(message = "Client's DNI is required")
    @ApiModelProperty(example = "45632951", required = true, position = 3)
    private Long dni;

    @NotNull(message = "Client's DNI type is required")
    @ApiModelProperty(example = "DNI", required = true, position = 4)
    private String documentType;

    @Email(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email format must be valid")
    @ApiModelProperty(notes = "Client's email address", example = "tomas@mail.com",  position = 5)
    private String email;
}
