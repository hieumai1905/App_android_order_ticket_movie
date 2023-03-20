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
import retrofit2.http.Query;

public interface CinemaAPI {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    CinemaAPI cinemaAPI = new Retrofit.Builder().baseUrl("http://192.168.0.101:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(CinemaAPI.class);

    @GET("cinemas")
    Call<List<Cinema>> getAllCinemas();

    @GET("cinemas/{cinemaId}")
    Call<Cinema> getCinemaById(@Path("cinemaId") String cinemaId);

    @GET("cinemas/movies")
    Call<List<Cinema>> getAllCinemaShowMovieByMovieId(@Path("id") String movieId);

    @GET("cinemas/search/cities/")
    Call<List<Cinema>> getCinemaByCity(@Query("city") String cityName);
}
