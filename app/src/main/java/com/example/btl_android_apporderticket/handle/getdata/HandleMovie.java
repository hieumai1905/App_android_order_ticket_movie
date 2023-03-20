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
                    String url = movie.getPoster();
                    if(url == null || url.isEmpty() || url.equals("N/A") || url.equals("")){
                        photos.add(new Photo("https://i.pinimg.com/564x/86/87/f3/8687f3c811454852d118abd25181cf22.jpg"));
                    } else {
                        photos.add(new Photo(url));
                    }
                }
            }
        } catch(Exception e) {
            throw new RuntimeException("Error photo of movies");
        }
        return photos;
    }
}
