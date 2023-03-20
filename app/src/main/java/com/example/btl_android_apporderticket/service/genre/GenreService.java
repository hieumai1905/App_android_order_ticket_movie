package com.example.btl_android_apporderticket.service.genre;

import com.example.btl_android_apporderticket.api.GenreAPI;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Genre;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreService implements IGenreService {

    private static GenreService instance;

    private GenreService() {
    }

    public static GenreService getInstanceGenreService() {
        if (instance == null) {
            instance = new GenreService();
        }
        return instance;
    }

    @Override
    public void getById(Integer key, IServiceCallback<Genre> callback) {
        GenreAPI.genreAPI.getGenreById(key).enqueue(new Callback<Genre>() {

            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void getAll(IServiceCallback<Iterable<Genre>> callback) {

    }

    @Override
    public void add(Genre entity, IServiceCallback<Genre> callback) {

    }

    @Override
    public void update(Integer key, Genre entity, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void remove(Integer key, IServiceCallback<Boolean> callback) {

    }
}
