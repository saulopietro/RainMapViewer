package com.RainMap.RainMap.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tb_alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp data;
    private String tipoOcorrencia;
    private String urgencia;

    @OneToOne
    private Address address;

    public Alert(Long id, String tipoOcorrencia, String urgencia, Address address, Timestamp data) {
        this.id = id;
        this.tipoOcorrencia = tipoOcorrencia;
        this.urgencia = urgencia;
        this.address = address;
        this.data = data;
    }

    public Alert(String tipoOcorrencia, String urgencia, Address address, Timestamp data) {
        this.tipoOcorrencia = tipoOcorrencia;
        this.urgencia = urgencia;
        this.address = address;
        this.data = data;
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

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }
}
