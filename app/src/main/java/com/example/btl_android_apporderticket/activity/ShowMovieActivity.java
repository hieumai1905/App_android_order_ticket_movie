package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.adapter.GenreAdapter;
import com.example.btl_android_apporderticket.handle.getdata.DataBuffer;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.service.movie.IMovieService;
import com.example.btl_android_apporderticket.service.movie.MovieService;

import java.util.List;

public class ShowMovieActivity extends Activity {
    private Button btnAllGenre, btnActionGenre, btnDramaGenre, btnHorrorGenre;
    private IMovieService movieService;
    private ListView lvMovie;
    private TextView tvBackMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_movie);

        initApp();
        loadData();
    }

    private void loadData() {
        movieService.getAll(new IServiceCallback<Iterable<Movie>>() {

            @Override
            public void onDataReceived(Iterable<Movie> data) {
                System.out.println("Get all movies successfully");
                displayMovies((List<Movie>) data);
            }

            @Override
            public void onRequestFailed(Throwable t) {
                System.out.println("Get all movies failed");
            }
        });
    }

    private void displayMovies(List<Movie> data) {
        GenreAdapter genreAdapter = new GenreAdapter(this, data);
        lvMovie.setAdapter(genreAdapter);
        lvMovie.setOnItemClickListener((parent, view, position, id) -> {
            if (DataBuffer.userCurrent != null) {
                Intent intent = new Intent(ShowMovieActivity.this, DetailMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", data.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ShowMovieActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initApp() {
        movieService = MovieService.getInstanceMovieService();
        btnAllGenre = findViewById(R.id.btnAllGenre);
        btnActionGenre = findViewById(R.id.btnActionGenre);
        btnDramaGenre = findViewById(R.id.btnDramaGenre);
        btnHorrorGenre = findViewById(R.id.btnHorrorGenre);
        lvMovie = findViewById(R.id.lvMovie);
        tvBackMovie = findViewById(R.id.tvBackMovie);

        tvBackMovie.setOnClickListener(v -> {
            finish();
        });

        btnAllGenre.setOnClickListener(v ->
        {
            loadData();
            resetSelectGenreMovie();
            btnAllGenre.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.select_nav));
        });
        btnActionGenre.setOnClickListener(v ->
        {
            showMovieInGenre(1);    // action
            resetSelectGenreMovie();
            btnActionGenre.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.select_nav));
        });
        btnDramaGenre.setOnClickListener(v ->
        {
            showMovieInGenre(2);    // drama
            resetSelectGenreMovie();
            btnDramaGenre.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.select_nav));
        });
        btnHorrorGenre.setOnClickListener(v ->
        {
            showMovieInGenre(3);    // horror
            resetSelectGenreMovie();
            btnHorrorGenre.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.select_nav));
        });
    }

    private void showMovieInGenre(int action) {
        movieService.getMovieByGenre(action, new IServiceCallback<Iterable<Movie>>() {
            @Override
            public void onDataReceived(Iterable<Movie> data) {
                System.out.println("Get all movies successfully");
                displayMovies((List<Movie>) data);
            }

            @Override
            public void onRequestFailed(Throwable t) {
                System.out.println("Get all movies failed");
            }
        });
    }

    private void resetSelectGenreMovie() {
        btnAllGenre.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.unchoose));
        btnActionGenre.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.unchoose));
        btnDramaGenre.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.unchoose));
        btnHorrorGenre.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.unchoose));
    }

}
