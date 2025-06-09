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

    public Alert(Long id, String tipoOcorrencia, String descricao, Double latitude, Double longitude, User user, Address address) {
        this.id = id;
        this.tipoOcorrencia = tipoOcorrencia;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
        this.address = address;
    }

    public Alert(String tipoOcorrencia, String descricao, Double latitude, Double longitude, User user, Address address) {
        this.tipoOcorrencia = tipoOcorrencia;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
        this.address = address;
    }

    public Alert() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(String tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
