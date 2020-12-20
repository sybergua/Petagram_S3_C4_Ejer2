package com.petagram_.models;

public class Perfil {
    private String profile_picture_url;
    private String name;

    public Perfil(String profile_picture_url, String name) {
        this.profile_picture_url = profile_picture_url;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "profile_picture_url='" + profile_picture_url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getProfile_picture_url() {
        return profile_picture_url;
    }

    public void setProfile_picture_url(String profile_picture_url) {
        this.profile_picture_url = profile_picture_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
