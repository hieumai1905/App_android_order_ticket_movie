package com.example.btl_android_apporderticket.service.cinema;

import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cinema;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.service.IGeneralService;

public interface ICinemaService extends IGeneralService<Cinema, String> {
    void getCinemaByCity(String cityName, IServiceCallback<Iterable<Cinema>> callback);
}
