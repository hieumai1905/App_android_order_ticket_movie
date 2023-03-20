package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.model.Genre;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GenreAPI {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    GenreAPI genreAPI = new Retrofit.Builder().baseUrl("http://192.168.0.101:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(GenreAPI.class);

    @GET("genres/{id}")
    Call<Genre> getGenreById(@Path("id") int id);
}
