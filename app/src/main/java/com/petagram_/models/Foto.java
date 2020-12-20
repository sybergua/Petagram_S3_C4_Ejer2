package com.petagram_.models;

public class Foto {
    private String foto;
    private int raiting;

    public Foto(){

    }

    public Foto(String foto, int raiting){
        this.foto = foto;
        this.raiting = raiting;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }
}
