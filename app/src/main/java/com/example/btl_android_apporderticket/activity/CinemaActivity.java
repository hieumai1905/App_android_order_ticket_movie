package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.adapter.CinemaAdapter;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cinema;
import com.example.btl_android_apporderticket.service.cinema.CinemaService;
import com.example.btl_android_apporderticket.service.cinema.ICinemaService;

import java.util.List;

public class CinemaActivity extends Activity {

    private Button btnCinemaHaNoi, btnCinemaHCM, btnCinemaDaNang, btnCinemaHue;
    private ICinemaService cinemaService;
    private List<Cinema> listCinemas;
    private ListView lvCinema;

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
        btnCinemaHue = findViewById(R.id.btnCinemaHue);
        lvCinema = findViewById(R.id.lvCinema);
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
    }
}
