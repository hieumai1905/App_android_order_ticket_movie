package com.example.btl_android_apporderticket.handle.getdata;

import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class HandleMovie {
    public static List<Photo> getPhotoOfMovie(List<Movie> listMovies) {
        List<Photo> photos = new ArrayList<>();
        try {
            if(listMovies != null) {
                for (Movie movie : listMovies) {
                    photos.add(new Photo(movie.getPoster()));
                }
            }
        } catch(Exception e) {
            throw new RuntimeException("Error photo of movies");
        }
        return photos;
    }
}
