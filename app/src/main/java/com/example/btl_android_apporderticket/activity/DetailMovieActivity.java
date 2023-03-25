package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cast;
import com.example.btl_android_apporderticket.model.Genre;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.service.cast.CastService;
import com.example.btl_android_apporderticket.service.cast.ICastService;
import com.example.btl_android_apporderticket.service.genre.GenreService;
import com.example.btl_android_apporderticket.service.genre.IGenreService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class DetailMovieActivity extends Activity {
    private TextView tvBackDetailMovie;
    private TextView tvTitleDetailMovie;
    private TextView tvDurationDetailMovie;
    private TextView tvGenreDetailMovie;
    private TextView tvDescriptionDetailMovie;
    private TextView tvReleaseDateDetailMovie;
    private TextView tvAgeRatingDetailMovie;
    private TextView tvSubTitleDetailMovie;
    private TextView tvDirectorDetailMovie;
    private TextView tvCastsDetailMovie;
    private TextView tvCountryDetailMovie;
    private ImageView imgPosterDetailMovie;
    private Button btnBookTicketDetailMovie;
    private IGenreService genreService;
    private ICastService castService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);
        initApp();
        try {
            loadData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws ParseException {
        Intent intent = getIntent();
        Movie movieShow = (Movie) intent.getSerializableExtra("movie-show");
        if (movieShow != null) {
            tvTitleDetailMovie.setText(movieShow.getTitle());
            tvDurationDetailMovie.setText(movieShow.getDuration() + " minutes");
            getGenre(movieShow.getGenreId());
            tvDescriptionDetailMovie.setText(movieShow.getDescription());
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = inputFormat.parse(movieShow.getReleasedDate());
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("vi", "VN"));
            tvReleaseDateDetailMovie.setText(outputFormat.format(date));
            tvAgeRatingDetailMovie.setText("C " + movieShow.getAgeRating() + " - No Children Under " + movieShow.getAgeRating() + " Years Old");
            tvSubTitleDetailMovie.setText("Phụ đề " + movieShow.getLanguage());
            tvDirectorDetailMovie.setText(movieShow.getDirector());
            getCast(movieShow.getMovieId());
            tvCountryDetailMovie.setText(movieShow.getCountry());
            Glide.with(this).load(movieShow.getPoster()).centerCrop().into(imgPosterDetailMovie);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (movieShow.getReleasedDate().compareTo(String.valueOf(LocalDateTime.now())) > 0) {
                    btnBookTicketDetailMovie.setVisibility(View.GONE);
                } else {
                    btnBookTicketDetailMovie.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void getCast(String movieId) {
        castService.getAllCastInMovie(movieId, new IServiceCallback<Iterable<Cast>>() {
            @Override
            public void onDataReceived(Iterable<Cast> data) {
                setCast(data);
            }

            @Override
            public void onRequestFailed(Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    private void setCast(Iterable<Cast> casts) {
        String cast = "";
        for (Cast c : casts) {
            cast += c.getName() + ", ";
        }
        tvCastsDetailMovie.setText(cast);
    }

    private void getGenre(int genreId) {
        genreService.getById(genreId, new IServiceCallback<Genre>() {
            @Override
            public void onDataReceived(Genre data) {
                setGenre(data);
            }

            @Override
            public void onRequestFailed(Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    private void setGenre(Genre data) {
        tvGenreDetailMovie.setText(data.getName());
    }


    private void initApp() {
        genreService = GenreService.getInstanceGenreService();
        castService = CastService.getInstanceCastService();
        tvBackDetailMovie = findViewById(R.id.tvBackDetailMovie);
        tvTitleDetailMovie = findViewById(R.id.tvTitleDetailMovie);
        tvDurationDetailMovie = findViewById(R.id.tvDurationDetailMovie);
        tvGenreDetailMovie = findViewById(R.id.tvGenreDetailMovie);
        tvDescriptionDetailMovie = findViewById(R.id.tvDescriptionDetailMovie);
        tvReleaseDateDetailMovie = findViewById(R.id.tvReleaseDateDetailMovie);
        tvAgeRatingDetailMovie = findViewById(R.id.tvAgeRatingDetailMovie);
        tvSubTitleDetailMovie = findViewById(R.id.tvSubTitleDetailMovie);
        tvDirectorDetailMovie = findViewById(R.id.tvDirectorDetailMovie);
        tvCastsDetailMovie = findViewById(R.id.tvCastsDetailMovie);
        tvCountryDetailMovie = findViewById(R.id.tvCountryDetailMovie);
        imgPosterDetailMovie = findViewById(R.id.imgPosterDetailMovie);
        btnBookTicketDetailMovie = findViewById(R.id.btnBookTicketDetailMovie);

        tvBackDetailMovie.setOnClickListener(v -> {
            finish();
        });

        btnBookTicketDetailMovie.setOnClickListener(v -> {
            System.out.println("Select book movie");
        });
    }
}
