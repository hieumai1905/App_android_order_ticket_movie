package com.example.btl_android_apporderticket.viewmodel;

import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.model.Schedule;

import java.util.List;

public class MovieTime {
    private String idMovie;
    private String nameMovie;
    private Movie movie;

    private List<Schedule> scheduleList;

    public MovieTime() {
    }

    public MovieTime(String idMovie, String nameMovie, Movie movie, List<Schedule> scheduleList) {
        this.idMovie = idMovie;
        this.nameMovie = nameMovie;
        this.scheduleList = scheduleList;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
