package com.example.btl_android_apporderticket.service.schedule;

import com.example.btl_android_apporderticket.api.ScheduleAPI;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleService implements IScheduleService {
    private static IScheduleService instance;

    private ScheduleService() {

    }

    public static IScheduleService getInstanceScheduleService() {
        if (instance == null) {
            instance = new ScheduleService();
        }
        return instance;
    }

    @Override
    public void getById(String key, IServiceCallback<Schedule> callback) {

    }

    @Override
    public void getAll(IServiceCallback<Iterable<Schedule>> callback) {
        ScheduleAPI.scheduleAPI.getAllSchedules().enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                callback.onDataReceived(response.body());
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }

    @Override
    public void add(Schedule entity, IServiceCallback<Schedule> callback) {

    }

    @Override
    public void update(String key, Schedule entity, IServiceCallback<Boolean> callback) {

    }

    @Override
    public void remove(String key, IServiceCallback<Boolean> callback) {

    }


    @Override
    public void getAllScheduleByMovieId(String idMovie, IServiceCallback<Iterable<Schedule>> callback) {
        ScheduleAPI.scheduleAPI.getAllScheduleByMovieId(idMovie).enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                callback.onDataReceived(response.body());
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                callback.onRequestFailed(t);
            }
        });
    }
}
