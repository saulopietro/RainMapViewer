package com.RainMap.RainMap.dto;

import com.RainMap.RainMap.models.Address;
import com.RainMap.RainMap.models.Alert;
import com.RainMap.RainMap.models.User;

import java.sql.Timestamp;

public class AlertDTO {

    private Long id;
    private String tipoOcorrencia;
    private String urgencia;
    private Timestamp data;
    private Address address;

    public AlertDTO(Long id, String tipoOcorrencia, String urgencia, Address address, Timestamp data) {
        this.id = id;
        this.tipoOcorrencia = tipoOcorrencia;
        this.urgencia = urgencia;
        this.address = address;
        this.data = data;
    }

    public AlertDTO() {
    }

    public AlertDTO(Alert alert) {
        id = alert.getId();
        tipoOcorrencia = alert.getTipoOcorrencia();
        urgencia = alert.getUrgencia();
        address = alert.getAddress();
        data = alert.getData();
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
