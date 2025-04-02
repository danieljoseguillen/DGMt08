package com.tarea8.dgmt08e04.Servicios;

import jakarta.validation.constraints.NotNull;

public class formInfo {

    @NotNull
    private Long voto;

    public Long getVoto() {
        return voto;
    }
    public void setVoto(Long voto) {
        this.voto = voto;
    }
}
