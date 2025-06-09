package com.RainMap.RainMap.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private Integer numero;
    private String bairro;

}
