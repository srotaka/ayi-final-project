package com.ayi.academy.app.entities;

import com.ayi.academy.app.enums.DocumentType;
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

    @ApiModelProperty(notes = "Client's first name",position = 1)
    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message = "Only letters allowed" ) //^[a-zA-Z]*(?:\s[a-zA-z]*)
    private String firstName;

    @ApiModelProperty(notes = "Client's last name",position = 2)
    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "Last Name is required")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="Only letters allowed" )
    private String lastName;

    @ApiModelProperty(notes = "Client's DNI",position = 3)
    @Column(name = "dni", nullable = false, length = 15)
    @NotBlank(message = "DNI is required")
    @Positive(message = "DNI cannot be a negative number")
    @Min(value = 10000000,message = "DNI must have at least 8 digits")
    private Long dni;

    @ApiModelProperty(notes = "Client's DNI",position = 4)
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 9)
    @NotBlank(message = "DNI Type is required")
    private DocumentType documentType;

    @ApiModelProperty(notes = "Client's email address",position = 5)
    @Email(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",
            message = "Mail format must be valid")
    @Column(name = "email", unique = true, length = 50)
    private String email;

    @ApiModelProperty(notes = "Client logic delete flag",position = 6)
    @Column(name = "is_active")
    private boolean isActive = Boolean.FALSE;

    /*
    @ApiModelProperty(notes = "Client date of registration",position = 7)
    @CreatedDate
    @Column( updatable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate creationDate;

    @ApiModelProperty(notes = "Client date of modifcation)
    @LastModifiedDate
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate  modificationDate;
     */
}
