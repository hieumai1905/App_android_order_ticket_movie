package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.config.Configuration;
import com.example.btl_android_apporderticket.model.Seat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SeatAPI {
    SeatAPI seatAPI = new Retrofit.Builder().baseUrl(Configuration.URL_BASE).

            addConverterFactory(GsonConverterFactory.create(Configuration.gson)).

            build().

            create(SeatAPI.class);

    @GET("seats")
    Call<List<Seat>> getAllSeats();


    @GET("seats/schedules/{idSchedule}")
    Call<List<Seat>> getAllSeatByScheduleId(@Path("idSchedule") String idSchedule);
}
