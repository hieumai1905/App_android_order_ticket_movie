package com.example.btl_android_apporderticket.service.cinema;

import com.example.btl_android_apporderticket.api.CinemaAPI;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cinema;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CinemaService implements ICinemaService {

    private static CinemaService instance;


    private CinemaService() {

    }

    public static CinemaService getInstanceCinemaService() {
        if (instance == null) {
            instance = new CinemaService();
        }
        return instance;
    }

    @Override
    public void getById(String key, IServiceCallback<Cinema> callback) {
        CinemaAPI.cinemaAPI.getCinemaById(key).enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });

    }

    @Override
    public void getAll(IServiceCallback<Iterable<Cinema>> callback) {
        CinemaAPI.cinemaAPI.getAllCinemas().enqueue(new Callback<List<Cinema>>() {

            @Override
            public void onResponse(Call<List<Cinema>> call, Response<List<Cinema>> response) {
                if (response.isSuccessful()) {
                    Iterable<Cinema> cinemas = response.body();
                    callback.onDataReceived(cinemas);
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Cinema>> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void add(Cinema entity, IServiceCallback<Cinema> callback) {

    }

    @Override
    public void update(String key, Cinema entity, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void remove(String key, IServiceCallback<Boolean> callback) {

    }
}
