package com.example.btl_android_apporderticket.model;

import java.io.Serializable;
import java.util.List;

public class Cinema implements Serializable {
    private String idCinema;
    private String nameCinema;
    private String addressCinema;
    private String imageCinema;
    private String phoneCinema;
    private List<Movie> movies;

    public Cinema() {
    }

    public Cinema(String idCinema, String nameCinema, String addressCinema, String imageCinema, String phoneCinema, List<Movie> movies) {
        this.idCinema = idCinema;
        this.nameCinema = nameCinema;
        this.addressCinema = addressCinema;
        this.phoneCinema = phoneCinema;
        this.imageCinema = imageCinema;
        this.movies = movies;
    }

    public String getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(String idCinema) {
        this.idCinema = idCinema;
    }

    public String getNameCinema() {
        return nameCinema;
    }

    public void setNameCinema(String nameCinema) {
        this.nameCinema = nameCinema;
    }

    public String getPhoneCinema() {
        return phoneCinema;
    }

    public void setPhoneCinema(String phoneCinema) {
        this.phoneCinema = phoneCinema;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getAddressCinema() {
        return addressCinema;
    }

    public void setAddressCinema(String addressCinema) {
        this.addressCinema = addressCinema;
    }

    public String getImageCinema() {
        return imageCinema;
    }

    public void setImageCinema(String imageCinema) {
        this.imageCinema = imageCinema;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "idCinema='" + idCinema + '\'' +
                ", nameCinema='" + nameCinema + '\'' +
                ", addressCinema='" + addressCinema + '\'' +
                ", imageCinema='" + imageCinema + '\'' +
                ", phoneCinema='" + phoneCinema + '\'' +
                ", movies=" + movies +
                '}';
    }
}
