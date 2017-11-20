package com.example.CompuCom2.model;

import java.time.LocalDateTime;

public class StatusShippingModel {

    private int id;
    private String status;
    private LocalDateTime last_update;

    public StatusShippingModel(){}

    public StatusShippingModel(int id, String status, LocalDateTime last_update) {
        this.id = id;
        this.status = status;
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "StatusShippingModel{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", last_update=" + last_update +
                '}';
    }
}
