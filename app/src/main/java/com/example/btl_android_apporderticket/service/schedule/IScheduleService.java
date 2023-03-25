package com.example.btl_android_apporderticket.service.schedule;


import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Schedule;
import com.example.btl_android_apporderticket.service.IGeneralService;

public interface IScheduleService extends IGeneralService<Schedule, String> {
    void getAllScheduleByMovieId(String idMovie, IServiceCallback<Iterable<Schedule>> callback);
}
