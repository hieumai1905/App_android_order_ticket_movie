package com.example.btl_android_apporderticket.service.seat;

import com.example.btl_android_apporderticket.api.SeatAPI;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Seat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeatService implements ISeatService {
    private static ISeatService _instance;

    private SeatService() {

    }

    public static ISeatService getInstanceSeatService() {
        if (_instance == null) {
            _instance = new SeatService();
        }
        return _instance;
    }

    @Override
    public void getById(Integer key, IServiceCallback<Seat> callback) {

    }

    @Override
    public void getAll(IServiceCallback<Iterable<Seat>> callback) {

    }

    @Override
    public void add(Seat entity, IServiceCallback<Seat> callback) {

    }

    @Override
    public void update(Integer key, Seat entity, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void remove(Integer key, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void getAllSeatByIdSchedule(String idSchedule, IServiceCallback<Iterable<Seat>> callback) {
        SeatAPI.seatAPI.getAllSeatByScheduleId(idSchedule).enqueue(new Callback<List<Seat>>() {
            @Override
            public void onResponse(Call<List<Seat>> call, Response<List<Seat>> response) {
                if (response.body() != null)
                    callback.onDataReceived(response.body());
                else
                    System.out.println("response code" + response.code());
            }

            @Override
            public void onFailure(Call<List<Seat>> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }
}
