package com.petagram_.models;

public class Foto {
    private String foto;
    private int raiting;
    private int comments_count;
    private String id;

    public Foto(){

    }

    public Foto(String foto, int raiting, int comments_count, String id) {
        this.foto = foto;
        this.raiting = raiting;
        this.comments_count = comments_count;
        this.id = id;
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

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
