package com.petagram_.restApi.model;

public class UsuarioResponse {
    private String id;
    private String idDispositivo;
    private String usuarioInstagram;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String id, String idDispositivo, String usuarioInstagram) {
        this.id = id;
        this.idDispositivo = idDispositivo;
        this.usuarioInstagram = usuarioInstagram;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getUsuarioInstagram() {
        return usuarioInstagram;
    }

    public void setUsuarioInstagram(String usuarioInstagram) {
        this.usuarioInstagram = usuarioInstagram;
    }
}
