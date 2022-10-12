package com.ayi.academy.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(
        value = "AddressResponsePages",
        description = "Address list paginated")
public class AddressResponsePages implements Serializable {

    @ApiModelProperty(position = 1, notes = "List of addresses")
    private List<AddressResponseDTO> addressResponseList;
    @ApiModelProperty(position = 2, notes = "Total pages")
    private Integer totalPages;
    @ApiModelProperty(position = 3, notes = "Current pages")
    private Integer currentPage;
    @ApiModelProperty(position = 4, notes = "Items by page")
    private Integer addressesPerPage;
    @ApiModelProperty(position = 5, notes = "size")
    private Integer totalAddresses;

}
