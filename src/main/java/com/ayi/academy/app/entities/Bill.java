package com.ayi.academy.app.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="bills")
@SQLDelete(sql = "UPDATE bills SET is_active = false WHERE id = ?")
@Where(clause = "is_active = false")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique id of a bill detail")
    private Integer id;

    @Column(name = "description", nullable = false, length = 200)
    @NotBlank(message = "Description of the purchase is required")
    @ApiModelProperty(notes = "Description of the purchase",position = 1)
    private String description;

    @Column(name = "total_amount", nullable = false, length = 8)
    @NotBlank(message = "Total amount of the purchase is required")
    @DecimalMin(value = "0.1", message = "Amount must be greater than 0.1")
    @ApiModelProperty(notes = "Total amount of the purchase",position = 2)
    private Double totalAmount;

    @Column(name = "is_active")
    @ApiModelProperty(notes = "Bill detail logic delete flag",position = 3)
    private boolean isActive = Boolean.FALSE;
}
