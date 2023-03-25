package com.example.btl_android_apporderticket.service.seat;

import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Seat;
import com.example.btl_android_apporderticket.service.IGeneralService;

public interface ISeatService extends IGeneralService<Seat, Integer> {
    void getAllSeatByIdSchedule(String idSchedule, IServiceCallback<Iterable<Seat>> callback);
}
