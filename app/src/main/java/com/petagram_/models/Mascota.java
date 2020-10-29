package com.petagram_.models;

public class Mascota {
    private String nombreMascota;
    private int fotoMascota;
    private int valorRaiting;
    private boolean sexo;

    public Mascota(String nombreMascota, int fotoMascota, int valorRaiting, boolean sexo) {
        this.nombreMascota = nombreMascota;
        this.fotoMascota = fotoMascota;
        this.valorRaiting = valorRaiting;
        this.sexo = sexo;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public int getFotoMascota() {
        return fotoMascota;
    }

    public void setFotoMascota(int fotoMascota) {
        this.fotoMascota = fotoMascota;
    }

    public int getValorRaiting() {
        return valorRaiting;
    }

    public void setValorRaiting(int valorRaiting) {
        this.valorRaiting = valorRaiting;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
}
