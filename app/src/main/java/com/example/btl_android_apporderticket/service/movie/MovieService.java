package com.example.btl_android_apporderticket.service.movie;

import androidx.annotation.NonNull;

import com.example.btl_android_apporderticket.api.MovieAPI;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieService implements IMovieService {

    private static IMovieService instance;

    private MovieService() {
    }

    public static IMovieService getInstanceMovieService() {
        if (instance == null) {
            instance = new MovieService();
        }
        return instance;
    }

    @Override
    public void getById(String key, IServiceCallback<Movie> callback) {
        MovieAPI.movieAPI.getMovieById(key).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void getAll(IServiceCallback<Iterable<Movie>> callback) {
        MovieAPI.movieAPI.getAllMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    Iterable<Movie> movies = response.body();
                    callback.onDataReceived(movies);
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void add(Movie entity, IServiceCallback<Movie> callback) {

    }

    @Override
    public void update(String key, Movie entity, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void remove(String key, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void getMovieByName(String name, IServiceCallback<Iterable<Movie>> callback) {
        MovieAPI.movieAPI.getAllMovieByName(name).enqueue(new Callback<List<Movie>>() {

            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    Iterable<Movie> movies = response.body();
                    callback.onDataReceived(movies);
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void getMovieByGenre(int id, IServiceCallback<Iterable<Movie>> callback) {
        MovieAPI.movieAPI.getAllMovieByGenre(id).enqueue(new Callback<List<Movie>>() {

            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    Iterable<Movie> movies = response.body();
                    callback.onDataReceived(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }
}
