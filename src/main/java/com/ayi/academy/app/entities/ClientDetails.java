package com.ayi.academy.app.entities;

import lombok.*;
import javax.persistence.*;

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

    @Column(name = "points")
    private Long points;

    @Column(name = "is_vip")
    private Boolean isVIP;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client clientId;

}
