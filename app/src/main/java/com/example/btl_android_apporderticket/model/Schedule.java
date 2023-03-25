package com.example.btl_android_apporderticket.model;

public class Schedule {
    private String scheduleId;
    private String showTime;
    private String endTime;
    private int price;
    private String movieId;

    public Schedule() {
    }

    public Schedule(String scheduleId, String showTime, String endTime, int price, String movieId) {
        this.scheduleId = scheduleId;
        this.showTime = showTime;
        this.endTime = endTime;
        this.price = price;
        this.movieId = movieId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", showTime='" + showTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", price=" + price +
                ", movieId='" + movieId + '\'' +
                '}';
    }
}
