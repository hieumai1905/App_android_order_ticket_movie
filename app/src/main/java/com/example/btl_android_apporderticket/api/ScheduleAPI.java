package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.config.Configuration;
import com.example.btl_android_apporderticket.model.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ScheduleAPI {
    ScheduleAPI scheduleAPI = new Retrofit.Builder().baseUrl(Configuration.URL_BASE).

            addConverterFactory(GsonConverterFactory.create(Configuration.gson)).

            build().

            create(ScheduleAPI.class);

    @GET("Schedules")
    Call<List<Schedule>> getAllSchedules();

    @GET("Schedules/movies/{id}")
    Call<List<Schedule>> getAllScheduleByMovieId(@Path("id") String idMovie);
}
