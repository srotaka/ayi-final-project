package com.ayi.academy.app.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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

    private String description;

    private Double totalAmount;
}
