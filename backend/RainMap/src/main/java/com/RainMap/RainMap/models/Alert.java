package com.RainMap.RainMap.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoOcorrencia;
    private String descricao;
    private Double latitude;
    private Double longitude;

    @OneToOne
    private User user;

    @OneToOne
    private Address address;
}
