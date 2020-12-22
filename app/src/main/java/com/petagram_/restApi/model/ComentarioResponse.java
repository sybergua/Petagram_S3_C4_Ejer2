package com.petagram_.restApi.model;

public class ComentarioResponse {
    private String id;

    public ComentarioResponse() {
    }

    public ComentarioResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
