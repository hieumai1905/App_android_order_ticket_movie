package com.example.btl_android_apporderticket.viewmodel;

public class MovieTicket {
    private String nameMovie;
    private String seatName;

    public MovieTicket(String nameMovie, String seatName) {
        this.nameMovie = nameMovie;
        this.seatName = seatName;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }
}
