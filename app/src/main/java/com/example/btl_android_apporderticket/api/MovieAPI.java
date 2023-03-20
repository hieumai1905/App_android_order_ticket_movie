package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.model.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    MovieAPI movieAPI = new Retrofit.Builder().baseUrl("http://192.168.0.101:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(MovieAPI.class);

    @GET("movies")
    Call<List<Movie>> getAllMovies();

    @GET("movies/{movieId}")
    Call<Movie> getMovieById(@Path("movieId") String movieId);

    @GET("Movies/search/name")
    Call<List<Movie>> getAllMovieByName(@Query("title") String title);

    @GET("Movies/search/genres")
    Call<List<Movie>> getAllMovieByGenre(@Query("id") int id);
}
