package com.ayi.academy.app.entities;

import com.ayi.academy.app.enums.ClientType;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "client_details")
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
    @Positive(message = "Points cannot be lower than 0")
    private Long points;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client clientId;

}
