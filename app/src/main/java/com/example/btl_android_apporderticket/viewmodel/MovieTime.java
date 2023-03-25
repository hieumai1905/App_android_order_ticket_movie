package com.example.btl_android_apporderticket.viewmodel;

import com.example.btl_android_apporderticket.handle.getdata.HandleTime;
import com.example.btl_android_apporderticket.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class MovieTime {
    private String idMovie;
    private String nameMovie;

    private List<Schedule> scheduleList;

    public MovieTime() {
    }

    public MovieTime(String idMovie, String nameMovie, List<Schedule> scheduleList) {
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

    public List<String> getScheduleListString() {
        List<String> scheduleListString = new ArrayList<>();
        for (Schedule schedule : this.scheduleList) {
            scheduleListString.add(HandleTime.getHourAndMinute(schedule.getShowTime()));
        }
        return scheduleListString;
    }
}
