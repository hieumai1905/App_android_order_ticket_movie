package com.example.btl_android_apporderticket.service.movie;

import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.service.IGeneralService;

public interface IMovieService extends IGeneralService<Movie, String> {
     void getMovieByName(String name, IServiceCallback<Iterable<Movie>> callback);
     void getMovieByGenre(int id, IServiceCallback<Iterable<Movie>> callback);
}
