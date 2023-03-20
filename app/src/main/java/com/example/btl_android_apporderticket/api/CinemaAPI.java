package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.model.Cinema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CinemaAPI {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

<<<<<<< HEAD
    CinemaAPI cinemaAPI = new Retrofit.Builder().baseUrl("http://192.168.0.101:7221/api/")
=======
    CinemaAPI cinemaAPI = new Retrofit.Builder().baseUrl("http://172.20.10.2:7221/api/")
>>>>>>> 0805cb4b816d84520c295a6e49e8a4129bad3733
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(CinemaAPI.class);

    @GET("cinemas")
    Call<List<Cinema>> getAllCinemas();

    @GET("cinemas/{cinemaId}")
    Call<Cinema> getCinemaById(@Path("cinemaId") String cinemaId);

    @GET("cinemas/movies")
    Call<List<Cinema>> getAllCinemaShowMovieByMovieId(@Path("id") String movieId);
}
