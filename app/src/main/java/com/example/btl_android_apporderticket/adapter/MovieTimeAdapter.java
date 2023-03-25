package com.example.btl_android_apporderticket.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.activity.DetailMovieActivity;
import com.example.btl_android_apporderticket.handle.getdata.DataBuffer;
import com.example.btl_android_apporderticket.handle.mycallback.ICallbackEventClick;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.service.movie.MovieService;
import com.example.btl_android_apporderticket.viewmodel.MovieTime;

import java.util.List;

public class MovieTimeAdapter extends RecyclerView.Adapter<MovieTimeAdapter.MovieInCinemaViewHolder> {

    private Context context;
    private List<MovieTime> listMovieTime;
    private ICallbackEventClick callbackEventClickMovie;

    public MovieTimeAdapter(Context context, ICallbackEventClick callbackEventClickMovie) {
        this.context = context;
        this.callbackEventClickMovie = callbackEventClickMovie;
    }

    public void setData(List<MovieTime> list) {
        this.listMovieTime = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieInCinemaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_in_cinema, parent, false);
        return new MovieInCinemaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieInCinemaViewHolder holder, int position) {
        MovieTime movieTime = listMovieTime.get(position);
        if (movieTime != null) {
            holder.tvNameMovie.setText(movieTime.getNameMovie());

            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            holder.rvTime.setLayoutManager(manager);

            TimeAdapter timeAdapter = new TimeAdapter();
            timeAdapter.setData(movieTime.getScheduleList(), new ICallbackEventClick() {
                @Override
                public void onSelectObject(Object o) {
                    callbackEventClickMovie.onSelectObject(o);
                }
            });
            holder.rvTime.setAdapter(timeAdapter);
            holder.tvDetail.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    System.out.println("View detail movie id: " + movieTime.getIdMovie());
                    MovieService.getInstanceMovieService().getById(movieTime.getIdMovie(), new IServiceCallback<Movie>() {
                        @Override
                        public void onDataReceived(Movie data) {
                            Intent intent = new Intent(context, DetailMovieActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("movie-show", data);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onRequestFailed(Throwable t) {
                            System.out.println("View detail movie failed: " + t.getMessage());
                        }
                    });
                }
            });
            holder.rvTime.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                    DataBuffer.ID_MOVIE_CURRENT = movieTime.getIdMovie();
                    DataBuffer.movieCurrent = movieTime.getMovie();
                    return false;
                }

                @Override
                public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (listMovieTime != null)
            return listMovieTime.size();
        return 0;
    }

    public class MovieInCinemaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameMovie;
        private TextView tvDetail;
        private RecyclerView rvTime;

        public MovieInCinemaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameMovie = itemView.findViewById(R.id.tvNameMovieInCinema);
            rvTime = itemView.findViewById(R.id.rvSelectTime);
            tvDetail = itemView.findViewById(R.id.tvViewDetailMovie);
        }
    }
}
