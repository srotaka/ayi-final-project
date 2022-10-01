package com.ayi.academy.app.entities;

import com.ayi.academy.app.enums.ClientType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "client_details")
@SQLDelete(sql = "UPDATE client_details SET is_active = true WHERE id=?")
@Where(clause = "is_active = false")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_detail_id")
    private Integer clientDetailsId;

    @Column(nullable = false)
    @NotBlank(message = "Points are required")
    @Positive(message = "Points cannot be lower than 0")
    private Long points;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Client Type is required")
    private ClientType clientType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client clientId;

    @Column(name = "is_active")
    private boolean isActive = Boolean.FALSE;

}
