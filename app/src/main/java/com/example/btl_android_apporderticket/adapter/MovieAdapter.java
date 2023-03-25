package com.example.btl_android_apporderticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_android_apporderticket.handle.mycallback.ICallbackEventClick;
import com.example.btl_android_apporderticket.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> listMovies;
    private final Context context;
    private ICallbackEventClick callbackEventClick;
    private static int resourceItem, resourceImage;

    public MovieAdapter(Context context, List<Movie> list, int resourceItem, int resourceImage, ICallbackEventClick callbackEventClick) {
        this.context = context;
        listMovies = list;
        MovieAdapter.resourceItem = resourceItem;
        MovieAdapter.resourceImage = resourceImage;
        this.callbackEventClick = callbackEventClick;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourceItem, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = listMovies.get(position);
        if (movie != null) {
            String url = movie.getPoster();
            if (url == null || url.equals("N/A") || url.equals("")) {
                url = "https://i.pinimg.com/564x/86/87/f3/8687f3c811454852d118abd25181cf22.jpg";
            }
            Glide.with(context).load(url).centerCrop().into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(movie.toString());
                    callbackEventClick.onSelectObject(movie);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (listMovies != null) {
            return listMovies.size();
        }
        return 0;
    }

    public Movie getItem(int position) {
        return listMovies.get(position);
    }



    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(resourceImage);
        }
    }
}