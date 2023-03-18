package com.example.btl_android_apporderticket.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.adapter.MovieAdapter;
import com.example.btl_android_apporderticket.handle.IServiceCallback;
import com.example.btl_android_apporderticket.handle.SlideRunnable;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.model.Photo;
import com.example.btl_android_apporderticket.model.User;
import com.example.btl_android_apporderticket.service.movie.IMovieService;
import com.example.btl_android_apporderticket.service.movie.MovieService;
import com.example.btl_android_apporderticket.service.user.IUserService;
import com.example.btl_android_apporderticket.service.user.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPagerTop;
    private ViewPager2 viewPagerCenter;
    private CircleIndicator3 indicatorTop;
    private List<Photo> listPhoto;
    private Handler myHandler;
    private SlideRunnable myRunnable;
    private User userCurrent;
    private List<Movie> listMovies;
    private IUserService userService;
    private IMovieService movieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApp();
        loadData();
    }

    private void initApp() {
        userService = new UserService();
        movieService = new MovieService();
    }

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
        setSlideTop();
        setSlideCenter();
    }

    private void register() {
        String birth = "2002-05-19";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(birth);
            formatter.applyPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String isoDateString = formatter.format(date);
        User user = new User("con huong dien", "012345", "huongdien@gmail.com", "huong", "nam", "ha noi", isoDateString);
        try {
            userService.add(user, new IServiceCallback<User>() {
                @Override
                public void onDataReceived(User data) {
                    Toast.makeText(MainActivity.this, data.getFullname(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRequestFailed(Throwable t) {
                    Toast.makeText(MainActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loginUser() {

        User user = new User("maihieu@gmail.com", "hieumai1905");
        try {
            userService.login(user, new IServiceCallback<User>() {
                @Override
                public void onDataReceived(User data) {
                    System.out.println(data.toString());
                    userCurrent = data;
                    System.out.println("login success");
                }

                @Override
                public void onRequestFailed(Throwable t) {
                    System.out.println("login failed");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSlideCenter() {
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


        MovieAdapter movieAdapter = new MovieAdapter(this, listMovies, R.layout.item_slide_center, R.id.item_image_center);
        viewPagerCenter.setAdapter(movieAdapter);


        if (movieAdapter.getItemCount() > 1) viewPagerCenter.setCurrentItem(1);
        viewPagerCenter.setPageTransformer(compositePageTransformer);
    }

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
