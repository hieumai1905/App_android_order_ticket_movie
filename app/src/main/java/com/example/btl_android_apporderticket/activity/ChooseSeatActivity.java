package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.btl_android_apporderticket.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseSeatActivity extends Activity {
    private TextView tvBackChooseMovie;
    private TextView tvTimeShowMovie;
    private TextView tvNameCinema;
    private TextView tvNameMovie;
    private TextView tvQuantitySeat;
    private TextView tvTotalPrice;
    private List<TextView> listSeat;
    private Button btnFinishChooseSeat;
    private int quantitySeat = 0;
    List<String> seatSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_seat);

        initApp();
        setEvent();
    }

    private void setEvent() {
        tvBackChooseMovie.setOnClickListener(v -> {
            finish();
        });
        Drawable iconSeat = ContextCompat.getDrawable(this, R.drawable.icon_seat);
//        for (TextView tv : listSeat) {
//            tv.setOnClickListener(v -> {
//                if (tv.isSelected()) {
//                    tv.setSelected(false);
//                    iconSeat.setColorFilter(ContextCompat.getColor(this, R.color.seat_default), PorterDuff.Mode.SRC_IN);
//                    tv.setCompoundDrawablesWithIntrinsicBounds(iconSeat, null, null, null);
//                    quantitySeat--;
//                } else {
//                    tv.setSelected(true);
//                    iconSeat.setColorFilter(ContextCompat.getColor(this, R.color.select_nav), PorterDuff.Mode.SRC_IN);
//                    tv.setCompoundDrawablesWithIntrinsicBounds(iconSeat, null, null, null);
//                    quantitySeat++;
//                }
//                tvQuantitySeat.setText(quantitySeat + "");
//            });
//        }
        for(int i = 0; i <  listSeat.size(); i++){
            int finalI = i;
            listSeat.get(i).setOnClickListener(v -> {
                if (listSeat.get(finalI).isSelected()) {
                    listSeat.get(finalI).setSelected(false);
                    iconSeat.setColorFilter(ContextCompat.getColor(this, R.color.seat_default), PorterDuff.Mode.SRC_IN);
                    listSeat.get(finalI).setCompoundDrawablesWithIntrinsicBounds(iconSeat, null, null, null);
                    System.out.println("UnChoose: -" + finalI);
                    quantitySeat--;
                } else {
                    listSeat.get(finalI).setSelected(true);
                    iconSeat.setColorFilter(ContextCompat.getColor(this, R.color.select_nav), PorterDuff.Mode.SRC_IN);
                    listSeat.get(finalI).setCompoundDrawablesWithIntrinsicBounds(iconSeat, null, null, null);
                    System.out.println("Choose: +" + finalI);
                    quantitySeat++;
                }
                tvQuantitySeat.setText(quantitySeat + "");
            });
        }
    }

    void saveSelectedSeat(String seat) {
        seatSelected.add(seat);
    }

    private void initApp() {
        seatSelected = new ArrayList<>();
        tvBackChooseMovie = findViewById(R.id.tvBackChooseSeat);
        tvNameCinema = findViewById(R.id.tvNameCinema);
        tvTimeShowMovie = findViewById(R.id.tvTimeShowMovie);
        tvNameMovie = findViewById(R.id.tvNameMovie);
        tvQuantitySeat = findViewById(R.id.tvQuantitySeat);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnFinishChooseSeat = findViewById(R.id.btnFinishChooseSeat);
        listSeat = new ArrayList<>();
        listSeat.add(findViewById(R.id.tvA1));
        listSeat.add(findViewById(R.id.tvA2));
        listSeat.add(findViewById(R.id.tvA3));
        listSeat.add(findViewById(R.id.tvA4));
        listSeat.add(findViewById(R.id.tvA5));
        listSeat.add(findViewById(R.id.tvA6));

        listSeat.add(findViewById(R.id.tvB1));
        listSeat.add(findViewById(R.id.tvB2));
        listSeat.add(findViewById(R.id.tvB3));
        listSeat.add(findViewById(R.id.tvB4));
        listSeat.add(findViewById(R.id.tvB5));
        listSeat.add(findViewById(R.id.tvB6));

        listSeat.add(findViewById(R.id.tvC1));
        listSeat.add(findViewById(R.id.tvC2));
        listSeat.add(findViewById(R.id.tvC3));
        listSeat.add(findViewById(R.id.tvC4));
        listSeat.add(findViewById(R.id.tvC5));
        listSeat.add(findViewById(R.id.tvC6));

        listSeat.add(findViewById(R.id.tvD1));
        listSeat.add(findViewById(R.id.tvD2));
        listSeat.add(findViewById(R.id.tvD3));
        listSeat.add(findViewById(R.id.tvD4));
        listSeat.add(findViewById(R.id.tvD5));
        listSeat.add(findViewById(R.id.tvD6));

        listSeat.add(findViewById(R.id.tvE1));
        listSeat.add(findViewById(R.id.tvE2));
        listSeat.add(findViewById(R.id.tvE3));
        listSeat.add(findViewById(R.id.tvE4));
        listSeat.add(findViewById(R.id.tvE5));
        listSeat.add(findViewById(R.id.tvE6));
    }
}
