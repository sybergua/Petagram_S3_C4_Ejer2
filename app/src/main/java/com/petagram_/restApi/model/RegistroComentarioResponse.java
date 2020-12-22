package com.petagram_.restApi.model;

public class RegistroComentarioResponse {
    private String respuesta;

    public RegistroComentarioResponse() {
    }

    public RegistroComentarioResponse(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
