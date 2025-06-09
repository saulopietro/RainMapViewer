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

    public Address(Long id, String endereco, Integer numero, String bairro) {
        this.id = id;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
    }

    public Address(String endereco, Integer numero, String bairro) {
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}
