package com.example.btl_android_apporderticket.service.cast;

import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cast;
import com.example.btl_android_apporderticket.model.Genre;
import com.example.btl_android_apporderticket.model.User;
import com.example.btl_android_apporderticket.service.IGeneralService;

public interface ICastService extends IGeneralService<Cast, Integer> {
    void getAllCastInMovie(String idMovie,  IServiceCallback<Iterable<Cast>> callback);
}
