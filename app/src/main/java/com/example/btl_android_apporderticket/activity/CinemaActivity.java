package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.adapter.CinemaAdapter;
import com.example.btl_android_apporderticket.handle.getdata.DataBuffer;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cinema;
import com.example.btl_android_apporderticket.service.cinema.CinemaService;
import com.example.btl_android_apporderticket.service.cinema.ICinemaService;

import java.util.List;

public class CinemaActivity extends Activity {

    private Button btnCinemaHaNoi, btnCinemaHCM, btnCinemaDaNang, btnCinemaAll;
    private ICinemaService cinemaService;
    private List<Cinema> listCinemas;
    private ListView lvCinema;
    private TextView tvBackCinema;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinemas);
        initApp();
        loadData();
    }

    private void initApp() {
        cinemaService = CinemaService.getInstanceCinemaService();
        btnCinemaHaNoi = findViewById(R.id.btnCinemaHaNoi);
        btnCinemaHCM = findViewById(R.id.btnCinemaHCM);
        btnCinemaDaNang = findViewById(R.id.btnCinemaDaNang);
        btnCinemaAll = findViewById(R.id.btnCinemaAll);
        lvCinema = findViewById(R.id.lvCinema);
        tvBackCinema = findViewById(R.id.tvBackCinema);

        tvBackCinema.setOnClickListener(v -> {
            finish();
        });

        btnCinemaHaNoi.setOnClickListener(v ->
        {
            showCinemaInArea("TP.HN");
            resetSelectAreaCinema();
            btnCinemaHaNoi.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.select_nav));
        });
        btnCinemaDaNang.setOnClickListener(v ->
        {
            showCinemaInArea("TP.DN");
            resetSelectAreaCinema();
            btnCinemaDaNang.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.select_nav));
        });
        btnCinemaAll.setOnClickListener(v ->
        {
            loadData();
            resetSelectAreaCinema();
            btnCinemaAll.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.select_nav));
        });
        btnCinemaHCM.setOnClickListener(v ->
        {
            showCinemaInArea("TP.HCM");
            resetSelectAreaCinema();
            btnCinemaHCM.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.select_nav));
        });
    }

    private void showCinemaInArea(String area) {
        cinemaService.getCinemaByCity(area, new IServiceCallback<Iterable<Cinema>>() {
            @Override
            public void onDataReceived(Iterable<Cinema> data) {
                System.out.println("Get " + area + " cinemas successfully");
                setDataCinema(data);
            }

            @Override
            public void onRequestFailed(Throwable t) {
                System.out.println("Get " + area + " cinemas failed");
            }
        });
    }

    // ---------------------------------------- load data----------------------------------------
    private void loadData() {
        cinemaService.getAll(new IServiceCallback<Iterable<Cinema>>() {
            @Override
            public void onDataReceived(Iterable<Cinema> data) {
                System.out.println("Get all cinemas successfully");
                setDataCinema(data);
            }

            @Override
            public void onRequestFailed(Throwable t) {
                System.out.println("Get all cinemas failed");
            }
        });
    }

    private void setDataCinema(Iterable<Cinema> data) {
        listCinemas = (List<Cinema>) data;
        displayCinema(listCinemas);
    }

    // ---------------------------------------- load data----------------------------------------

    private void displayCinema(List<Cinema> listCinemaCurrent) {
        CinemaAdapter adapterCinema = new CinemaAdapter(this, listCinemaCurrent);
        lvCinema.setAdapter(adapterCinema);
        lvCinema.setOnItemClickListener((parent, view, position, id) -> {
            Cinema cinema = listCinemaCurrent.get(position);
            DataBuffer.ID_CINEMA_CURRENT = cinema.getIdCinema();
            System.out.println(cinema.toString());
        });
        tvBackCinema.setOnClickListener(v -> {
            finish();
        });
    }

    private void resetSelectAreaCinema() {
        btnCinemaHaNoi.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.unchoose));
        btnCinemaDaNang.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.unchoose));
        btnCinemaHCM.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.unchoose));
        btnCinemaAll.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.unchoose));
    }
}
