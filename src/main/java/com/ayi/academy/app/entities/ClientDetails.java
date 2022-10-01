package com.ayi.academy.app.entities;

import com.ayi.academy.app.enums.ClientType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "client_details")
@Data
@SQLDelete(sql = "UPDATE client_details SET is_active = true WHERE id=?")
@Where(clause = "is_active = false")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Details about client")
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique id of a client detail")
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Points are required")
    @Positive(message = "Points cannot be lower than 0")
    @ApiModelProperty(notes = "Client's purchases points",position = 1)
    private Long points;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotBlank(message = "Client Type is required")
    @ApiModelProperty(notes = "Client type",position = 2)
    private ClientType clientType;


}
