package com.example.btl_android_apporderticket.api;

import com.example.btl_android_apporderticket.config.Configuration;
import com.example.btl_android_apporderticket.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    MovieAPI movieAPI = new Retrofit.Builder().baseUrl(Configuration.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(Configuration.gson)).build().create(MovieAPI.class);

    @GET("movies")
    Call<List<Movie>> getAllMovies();

    @GET("movies/{movieId}")
    Call<Movie> getMovieById(@Path("movieId") String movieId);

    @GET("Movies/search/name")
    Call<List<Movie>> getAllMovieByName(@Query("title") String title);

    @GET("Movies/search/genres")
    Call<List<Movie>> getAllMovieByGenre(@Query("id") int id);
}
