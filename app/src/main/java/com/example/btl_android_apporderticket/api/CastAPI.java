package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.model.Cast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CastAPI {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    CastAPI castAPI = new Retrofit.Builder().baseUrl("http://192.168.0.101:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(CastAPI.class);

    @GET("casts/movies")
    Call<List<Cast>> getAllCastByMovieId(@Query("id") String id);
}
