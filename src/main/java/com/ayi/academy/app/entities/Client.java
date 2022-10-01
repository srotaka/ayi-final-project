package com.ayi.academy.app.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "clients")
@SQLDelete(sql = "UPDATE clients SET is_active = true WHERE id=?")
@Where(clause = "is_active = false")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "Information about client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique id of a client")
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "First Name is required")
    @ApiModelProperty(notes = "Client's first name", position = 1)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "Last Name is required")
    @ApiModelProperty(notes = "Client's last name", position = 2)
    private String lastName;

    @Column(name = "dni", nullable = false, length = 15)
    @NotBlank(message = "DNI is required")
    @Positive(message = "DNI cannot be a negative number")
    @Min(value = 10000000, message = "DNI must have at least 8 digits")
    @ApiModelProperty(notes = "Client's DNI", position = 3)
    private Long dni;

    @Column(name = "document_type", nullable = false, length = 9)
    @NotBlank(message = "DNI Type is required")
    @ApiModelProperty(notes = "Client's DNI", position = 4)
    private String documentType;

    @Column(name = "email", unique = true, length = 50)
    @Email(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Mail format must be valid")
    @ApiModelProperty(notes = "Client's email address", position = 5)
    private String email;

    @Column(name = "is_active")
    @ApiModelProperty(notes = "Client logic delete flag", position = 6)
    private boolean isActive = Boolean.FALSE;
}
