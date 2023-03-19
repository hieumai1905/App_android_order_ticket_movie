package com.example.btl_android_apporderticket.activity;

import static com.example.btl_android_apporderticket.define.RequestCode.LOGIN_RESULT_CODE;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.adapter.MovieAdapter;
import com.example.btl_android_apporderticket.handle.autorun.SlideRunnable;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.model.Photo;
import com.example.btl_android_apporderticket.model.User;
import com.example.btl_android_apporderticket.service.movie.IMovieService;
import com.example.btl_android_apporderticket.service.movie.MovieService;
import com.example.btl_android_apporderticket.service.user.IUserService;
import com.example.btl_android_apporderticket.service.user.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPagerTop;
    private ViewPager2 viewPagerCenter;
    private CircleIndicator3 indicatorTop;
    private List<Photo> listPhoto;
    private Handler myHandler;
    private SlideRunnable myRunnable;

    private User userCurrent;
    private Movie movieCurrent;

    private List<Movie> listMovies;
    private List<Movie> listMovieNowShows;
    private List<Movie> listMovieComingSoons;
    private IUserService userService;
    private IMovieService movieService;

    private TextView titleMovie;
    private TextView durationMovie;
    private TextView ageRating;

    private Button btnNowShow;
    private Button btnComingSoon;

    private BottomNavigationView bottomNavigationView;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApp();
        loadData();
        registerHandlerResultActivity();
    }


    private void initApp() {
        userService = UserService.getInstanceUserService();
        movieService = new MovieService();
        listMovies = new ArrayList<>();
        listMovieNowShows = new ArrayList<>();
        listMovieComingSoons = new ArrayList<>();
        titleMovie = findViewById(R.id.tvTitleMovie);
        durationMovie = findViewById(R.id.tvDurationMovie);
        ageRating = findViewById(R.id.tvAgeRating);
        btnComingSoon = findViewById(R.id.btnComingSoon);
        btnNowShow = findViewById(R.id.btnNowShow);
        bottomNavigationView = findViewById(R.id.bottom_nav);


        btnNowShow.setOnClickListener(v -> {
//            setSlideCenter(listMovieNowShows);
            changeMovie(listMovieNowShows);
        });
        btnComingSoon.setOnClickListener(v -> {
//            setSlideCenter(listMovieComingSoons);
            changeMovie(listMovieComingSoons);
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    System.out.println("Home");
                    break;
                case R.id.nav_showtime:
                    System.out.println("Showtime");
                    break;
                case R.id.nav_personal: {
                    System.out.println("Personal");
                    if (userCurrent == null) {
                        System.out.println("User null");

                        // ---------------------------- request login ----------------------------

                        activityResultLauncher.launch(new Intent(MainActivity.this, LoginActivity.class));

                        //------------------------------------------------------------------------


                    }
                }
                break;
            }
            return true;
        });

    }

    //---------------------------------------load data-------------------------------------------

    private void loadData() {
        try {
            movieService.getAll(new IServiceCallback<Iterable<Movie>>() {

                @Override
                public void onDataReceived(Iterable<Movie> data) {
                    System.out.println("Get all movies success");
                    setDataMovie(data);
                }

                @Override
                public void onRequestFailed(Throwable t) {
                    System.out.println("All movies failed");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDataMovie(Iterable<Movie> data) {
        listMovies = (List<Movie>) data;
        for (Movie movie : listMovies) {
            // neu ngay hien tai lon hon ngay bat dau chieu thi phim dang chieu
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (movie.getReleasedDate().compareTo(String.valueOf(LocalDateTime.now())) < 0) {
                    listMovieNowShows.add(movie);
                } else {
                    listMovieComingSoons.add(movie);
                }
            }
        }
        setSlideTop();
        setSlideCenter(listMovieNowShows);
    }

    //---------------------------------------load data-------------------------------------------

    //---------------------------------------slide center-------------------------------------------

    private void setSlideCenter(List<Movie> listMoviesCurrents) {
        viewPagerCenter = findViewById(R.id.viewpagerCenter);
        viewPagerCenter.setOffscreenPageLimit(3);
        viewPagerCenter.setClipToPadding(false);
        viewPagerCenter.setClipChildren(false);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.9f + r * 0.1f);
            }
        });
        MovieAdapter movieAdapter = new MovieAdapter(this, listMoviesCurrents, R.layout.item_slide_center, R.id.item_image_center);
        viewPagerCenter.setAdapter(movieAdapter);

        if (movieAdapter.getItemCount() > 0 && movieAdapter.getItemCount() < 2) {
            updateUIMovie(movieAdapter.getItem(0));
        } else {
            viewPagerCenter.setCurrentItem(1);
            updateUIMovie(movieAdapter.getItem(1));
        }
        viewPagerCenter.setPageTransformer(compositePageTransformer);

        viewPagerCenter.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateUIMovie(movieAdapter.getItem(position));
            }
        });
    }

    private void updateUIMovie(Movie movie) {
        titleMovie.setText(movie.getTitle().toUpperCase());
        durationMovie.setText(movie.getDuration() + " minutes");
        ageRating.setText("C" + movie.getAgeRating());
        movieCurrent = movie;
        System.out.println("movie current " + movieCurrent.toString());
    }

    private void changeMovie(List<Movie> movies) {
        MovieAdapter movieAdapter = new MovieAdapter(this, movies, R.layout.item_slide_center, R.id.item_image_center);
        if (movieAdapter.getItemCount() > 0 && movieAdapter.getItemCount() < 2) {
            updateUIMovie(movieAdapter.getItem(0));
        } else {
            viewPagerCenter.setCurrentItem(1);
            updateUIMovie(movieAdapter.getItem(1));
        }
        viewPagerCenter.setAdapter(movieAdapter);
    }

    //---------------------------------------slide center-------------------------------------------

    //----------------------------------------slide top--------------------------------------------

    private void setSlideTop() {
        myHandler = new Handler(Looper.getMainLooper());
        viewPagerTop = findViewById(R.id.viewpager);
        indicatorTop = findViewById(R.id.indicator);
        viewPagerTop.setClipToPadding(false);
        viewPagerTop.setClipChildren(false);
        listPhoto = GetListPhoto();

        MovieAdapter movieAdapter = new MovieAdapter(this, listMovies, R.layout.item_slide_top, R.id.item_image_top);
        viewPagerTop.setAdapter(movieAdapter);

        indicatorTop.setViewPager(viewPagerTop);
        myRunnable = new SlideRunnable(viewPagerTop, listPhoto);
        myHandler.postDelayed(myRunnable, 2500);
        viewPagerTop.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                myHandler.removeCallbacks(myRunnable);
                myHandler.postDelayed(myRunnable, 2000);
            }
        });
    }

    //----------------------------------------slide top--------------------------------------------

    // --------------------------------------handle result ----------------------------------------

    private void registerHandlerResultActivity() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                int resultCode = result.getResultCode();
                switch (resultCode) {
                    case LOGIN_RESULT_CODE: {
                        userCurrent = (User) Objects.requireNonNull(result.getData()).getSerializableExtra("user");
                    }
                    break;
                }
            }
        });
    }

    //---------------------------------------------------------------------------------------------


    private List<Photo> GetListPhoto() {
        List<Photo> lists = new ArrayList<>();
        lists.add(new Photo("https://i.pinimg.com/564x/fe/01/68/fe0168e6131ff32b6de118c6fa824b10.jpg"));
        lists.add(new Photo("https://i.pinimg.com/564x/03/c0/17/03c017c2000802a34a48808e6eb444ae.jpg"));
        lists.add(new Photo("https://i.pinimg.com/564x/11/a3/c9/11a3c9e9bc20cb92b76126ce0748eedd.jpg"));
        lists.add(new Photo("https://i.pinimg.com/564x/0e/5b/5c/0e5b5c1818f4c238130388d4cafb5e57.jpg"));
        lists.add(new Photo("https://i.pinimg.com/564x/17/74/5e/17745e8bf641b87bb0687a5781a6f980.jpg"));
        lists.add(new Photo("https://i.pinimg.com/564x/be/64/18/be64183c8db691b0b2db185bb9f13aca.jpg"));
        return lists;
    }
}
