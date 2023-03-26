package com.example.btl_android_apporderticket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.hashcode.HashCodeToLong;
import com.example.btl_android_apporderticket.model.Movie;

import java.util.List;

public class GenreAdapter extends BaseAdapter {
    private List<Movie> listMovies;
    private Context context;

    public GenreAdapter(Context context, List<Movie> listMovies) {
        this.listMovies = listMovies;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (listMovies != null) return listMovies.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return listMovies.get(i);
    }

    @Override
    public long getItemId(int i) {
        String id = listMovies.get(i).getMovieId();
        return HashCodeToLong.getHashedId(id);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewMovie = view;
        if (view == null) {
            viewMovie = View.inflate(viewGroup.getContext(), R.layout.item_movie, null);
        }
        Movie movie = listMovies.get(i);
        ((TextView) viewMovie.findViewById(R.id.tvNameMovie)).setText(movie.getTitle());
        ((TextView) viewMovie.findViewById(R.id.tvLanguage)).setText(movie.getLanguage());
        ImageView imageView = viewMovie.findViewById(R.id.imgMovie);
        Glide.with(context).load(movie.getPoster()).centerCrop().into(imageView);
        return viewMovie;
    }
}
