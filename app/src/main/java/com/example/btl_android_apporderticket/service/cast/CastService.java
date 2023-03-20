package com.example.btl_android_apporderticket.service.cast;

import com.example.btl_android_apporderticket.api.CastAPI;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastService implements ICastService {

    private static CastService instance;

    private CastService() {
    }

    public static CastService getInstanceCastService() {
        if (instance == null) {
            instance = new CastService();
        }
        return instance;
    }

    @Override
    public void getById(Integer key, IServiceCallback<Cast> callback) {

    }

    @Override
    public void getAll(IServiceCallback<Iterable<Cast>> callback) {

    }

    @Override
    public void add(Cast entity, IServiceCallback<Cast> callback) {

    }

    @Override
    public void update(Integer key, Cast entity, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void remove(Integer key, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void getAllCastInMovie(String idMovie, IServiceCallback<Iterable<Cast>> callback) {
        CastAPI.castAPI.getAllCastByMovieId(idMovie).enqueue(new Callback<List<Cast>>() {
            @Override
            public void onResponse(Call<List<Cast>> call, Response<List<Cast>> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    callback.onRequestFailed(new Exception("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Cast>> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }
}
