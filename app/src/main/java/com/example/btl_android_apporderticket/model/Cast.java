package com.example.btl_android_apporderticket.model;

import java.util.List;

public class Cast {
    private String castId;
    private String name;
    private String birthDay;
    private String nationality;
    private List<Movie> movies;

    public Cast() {
    }

    public Cast(String castId, String name, String birthDay, String nationality, List<Movie> movies) {
        this.castId = castId;
        this.name = name;
        this.birthDay = birthDay;
        this.nationality = nationality;
        this.movies = movies;
    }

    public String getCastId() {
        return castId;
    }

    public void setCastId(String castId) {
        this.castId = castId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "castId='" + castId + '\'' +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", nationality='" + nationality + '\'' +
                ", movies=" + movies +
                '}';
    }
}
