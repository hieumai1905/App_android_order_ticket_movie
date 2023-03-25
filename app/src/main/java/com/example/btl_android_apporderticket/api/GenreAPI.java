package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.config.Configuration;
import com.example.btl_android_apporderticket.model.Genre;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GenreAPI {

    GenreAPI genreAPI = new Retrofit.Builder().baseUrl(Configuration.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(Configuration.gson)).build().create(GenreAPI.class);

    @GET("genres/{id}")
    Call<Genre> getGenreById(@Path("id") int id);
}
