package com.petagram_.restApi.model;

import com.petagram_.models.Foto;

import java.util.ArrayList;

public class FotosResponse {
    ArrayList<Foto> fotos;

    public ArrayList<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<Foto> fotos) {
        this.fotos = fotos;
    }
}
