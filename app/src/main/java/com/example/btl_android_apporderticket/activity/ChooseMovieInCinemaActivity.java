package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.adapter.MovieTimeAdapter;
import com.example.btl_android_apporderticket.handle.getdata.DataBuffer;
import com.example.btl_android_apporderticket.handle.getdata.HandleTime;
import com.example.btl_android_apporderticket.handle.mycallback.ICallbackEventClickMovie;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cinema;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.model.Schedule;
import com.example.btl_android_apporderticket.service.schedule.IScheduleService;
import com.example.btl_android_apporderticket.service.schedule.ScheduleService;
import com.example.btl_android_apporderticket.viewmodel.MovieTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChooseMovieInCinemaActivity extends Activity {
    private TextView tvCinemaName;
    private TextView tvCinemaAddress;
    private TextView tvCinemaPhone;
    private TextView tvBackDetailMovie;
    private MovieTimeAdapter movieTimeAdapter;
    private RecyclerView rvMovieTime;
    private Cinema cinemaCurrent;
    private List<Movie> listMovieOfCinema;

    private IScheduleService scheduleService;
    private List<MovieTime> listMovieTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_movie_for_cinema);
        initApp();
        setEvent();
        getData();
    }


    private void initApp() {
        listMovieTime = new ArrayList<>();
        scheduleService = ScheduleService.getInstanceScheduleService();
        tvCinemaName = findViewById(R.id.tvCinemaName);
        tvCinemaAddress = findViewById(R.id.tvCinemaAddress);
        tvCinemaPhone = findViewById(R.id.tvCinemaPhone);
        tvBackDetailMovie = findViewById(R.id.tvBackDetailMovie);
    }

    private void setEvent() {
        tvBackDetailMovie.setOnClickListener(v -> {
            finish();
        });
        tvCinemaPhone.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + cinemaCurrent.getPhoneCinema()));
            startActivity(intent);
        });
    }

    private void getData() {
        cinemaCurrent = (Cinema) getIntent().getSerializableExtra("cinema-current");
        tvCinemaName.setText(cinemaCurrent.getNameCinema());
        tvCinemaAddress.setText(cinemaCurrent.getAddressCinema());
        tvCinemaPhone.setText(cinemaCurrent.getPhoneCinema());
        getListMovieOfCinemaToDay();
        getTimeShowForMovie();
    }

    private void getListMovieOfCinemaToDay() {
        listMovieOfCinema = cinemaCurrent.getMovies();
    }


    private void getTimeShowForMovie() {
        try {
            final int[] count = {0};
            for (Movie movie : listMovieOfCinema) {
                scheduleService.getAllScheduleByMovieId(movie.getMovieId(), new IServiceCallback<Iterable<Schedule>>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDataReceived(Iterable<Schedule> data) {
                        setMovieTime(movie.getMovieId(), movie.getTitle(), data);
                        if (count[0] == listMovieOfCinema.size() - 1) {
                            loadData();
                        } else {
                            count[0]++;
                        }
                    }

                    @Override
                    public void onRequestFailed(Throwable t) {
                        System.out.println("onRequestFailed: " + t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMovieTime(String movieId, String title, Object data) {
        listMovieTime.add(new MovieTime(movieId, title, (List<Schedule>) data));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        movieTimeAdapter = new MovieTimeAdapter(ChooseMovieInCinemaActivity.this, new ICallbackEventClickMovie() {
            @Override
            public void onSelectMovie(Object o) {
                System.out.println("Time current : " + o.toString());
                System.out.println("Movie current : " + DataBuffer.ID_MOVIE_CURRENT);
                Intent intent = new Intent(ChooseMovieInCinemaActivity.this, ChooseSeatActivity.class);
                intent.putExtra("time-current", o.toString());
                startActivity(intent);
            }
        });
        rvMovieTime = findViewById(R.id.rvMovieInCinema);
        LinearLayoutManager manager = new LinearLayoutManager(ChooseMovieInCinemaActivity.this, LinearLayoutManager.VERTICAL, false);
        rvMovieTime.setLayoutManager(manager);
        movieTimeAdapter.setData(getListMovieTimeOfDay(new Date(), 0));
        rvMovieTime.setAdapter(movieTimeAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<MovieTime> getListMovieTimeOfDay(Date date, int day) {
        List<MovieTime> listMovieTimeCurrent = new ArrayList<>();
        for (MovieTime movieTime : listMovieTime) {
            List<Schedule> scheduleList = new ArrayList<>();
            for (Schedule schedule : movieTime.getScheduleList()) {
                if (HandleTime.isSameDay(date, schedule.getShowTime(), day)) {
                    scheduleList.add(schedule);
                }
            }
            if (scheduleList.size() > 0)
                listMovieTimeCurrent.add(new MovieTime(movieTime.getIdMovie(), movieTime.getNameMovie(), scheduleList));
        }

        return listMovieTimeCurrent;
    }
}
