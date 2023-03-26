package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.getdata.DataBuffer;
import com.example.btl_android_apporderticket.handle.getdata.HandleTime;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cinema;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.model.Schedule;
import com.example.btl_android_apporderticket.model.Seat;
import com.example.btl_android_apporderticket.service.movie.IMovieService;
import com.example.btl_android_apporderticket.service.movie.MovieService;
import com.example.btl_android_apporderticket.service.seat.ISeatService;
import com.example.btl_android_apporderticket.service.seat.SeatService;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ChooseSeatActivity extends Activity {
    private TextView tvBackChooseMovie;
    private TextView tvListSeatSelected;
    private TextView tvTimeShowMovie;
    private TextView tvNameCinema;
    private TextView tvNameMovie;
    private TextView tvQuantitySeat;
    private TextView tvTotalPrice;
    private TextView tvSelected;
    private TextView tvSold;
    private List<TextView> listSeat;
    private Button btnFinishChooseSeat;
    private int quantitySeat = 0;
    private List<String> seatSelected;
    private List<Seat> listSeatInSchedule;
    private Movie movieCurrent;
    private Schedule scheduleCurrent;
    private Cinema cinemaCurrent;

    private IMovieService movieService;
    private ISeatService seatService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_seat);

        initApp();
        setEvent();
    }

    private void loadData() {
        cinemaCurrent = (Cinema) getIntent().getSerializableExtra("cinema-current");
        tvNameCinema.setText(cinemaCurrent.getNameCinema());
        scheduleCurrent = DataBuffer.scheduleCurrent;
        tvNameMovie.setText(movieCurrent.getTitle());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(scheduleCurrent.getShowTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("vi", "VN"));
        tvTimeShowMovie.setText(outputFormat.format(date) + " Begin at: " + HandleTime.getHourAndMinute(scheduleCurrent.getShowTime()) + "-" + HandleTime.getHourAndMinute(scheduleCurrent.getEndTime()));
        getAllSeat();
    }

    private void getAllSeat() {
        seatService.getAllSeatByIdSchedule(scheduleCurrent.getScheduleId(), new IServiceCallback<Iterable<Seat>>() {
            @Override
            public void onDataReceived(Iterable<Seat> data) {
                listSeatInSchedule = (List<Seat>) data;
                setStatusForSeat();
            }

            @Override
            public void onRequestFailed(Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    private void setStatusForSeat() {
        System.out.println("List seat of schedule: " + listSeatInSchedule.size());
        Drawable iconSeat = ContextCompat.getDrawable(this, R.drawable.icon_seat);
        for (int i = 0; i < listSeatInSchedule.size(); i++) {
            if (Objects.equals(listSeatInSchedule.get(i).getStatus(), "1")) {
                listSeat.get(i).setSelected(true);
                iconSeat.setColorFilter(ContextCompat.getColor(this, R.color.booked), PorterDuff.Mode.SRC_IN);
                listSeat.get(i).setCompoundDrawablesWithIntrinsicBounds(iconSeat, null, null, null);
                listSeat.get(i).setEnabled(false);
            }
        }
    }

    private void getMovieCurrent() {
        movieService.getById(DataBuffer.ID_MOVIE_CURRENT, new IServiceCallback<Movie>() {
            @Override
            public void onDataReceived(Movie data) {
                movieCurrent = data;
                loadData();
            }

            @Override
            public void onRequestFailed(Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }


    private void setEvent() {
        tvBackChooseMovie.setOnClickListener(v -> {
            finish();
        });
        Drawable iconSeatSelected = ContextCompat.getDrawable(this, R.drawable.icon_seat);
        iconSeatSelected.setColorFilter(ContextCompat.getColor(this, R.color.select_nav), PorterDuff.Mode.SRC_IN);
        tvSelected.setCompoundDrawablesWithIntrinsicBounds(iconSeatSelected, null, null, null);
        Drawable iconSeatSold = ContextCompat.getDrawable(this, R.drawable.icon_seat);
        iconSeatSold.setColorFilter(ContextCompat.getColor(this, R.color.booked), PorterDuff.Mode.SRC_IN);
        tvSold.setCompoundDrawablesWithIntrinsicBounds(iconSeatSold, null, null, null);
        for (TextView tv : listSeat) {
            Drawable iconSeat = ContextCompat.getDrawable(this, R.drawable.icon_seat);
            iconSeat.setColorFilter(ContextCompat.getColor(this, R.color.seat_default), PorterDuff.Mode.SRC_IN);
            tv.setTag(iconSeat);
        }
        for (int i = 0; i < listSeat.size(); i++) {
            int finalI = i;
            Drawable iconSeat = ContextCompat.getDrawable(this, R.drawable.icon_seat);
            listSeat.get(i).setOnClickListener(v -> {
                if (listSeat.get(finalI).isSelected()) {
                    listSeat.get(finalI).setSelected(false);
                    iconSeat.setColorFilter(ContextCompat.getColor(this, R.color.seat_default), PorterDuff.Mode.SRC_IN);
                    listSeat.get(finalI).setCompoundDrawablesWithIntrinsicBounds(iconSeat, null, null, null);
                    System.out.println("UnChoose: -" + finalI);
                    if (finalI < 6) {
                        selectSeat(finalI, "A", false);
                    } else if (finalI >= 6 && finalI < 12) {
                        selectSeat(finalI - 6, "B", false);
                    } else if (finalI >= 12 && finalI < 18) {
                        selectSeat(finalI - 12, "C", false);
                    } else if (finalI >= 18 && finalI < 24) {
                        selectSeat(finalI - 18, "D", false);
                    } else {
                        selectSeat(finalI - 24, "E", false);
                    }
                    quantitySeat--;
                    setPrice();
                } else if (!listSeat.get(finalI).isSelected()) {
                    if (seatSelected.size() < 8) {
                        listSeat.get(finalI).setSelected(true);
                        iconSeat.setColorFilter(ContextCompat.getColor(this, R.color.select_nav), PorterDuff.Mode.SRC_IN);
                        listSeat.get(finalI).setCompoundDrawablesWithIntrinsicBounds(iconSeat, null, null, null);
                        System.out.println("Choose: +" + finalI);
                        if (finalI < 6) {
                            selectSeat(finalI, "A", true);
                        } else if (finalI >= 6 && finalI < 12) {
                            selectSeat(finalI - 6, "B", true);
                        } else if (finalI >= 12 && finalI < 18) {
                            selectSeat(finalI - 12, "C", true);
                        } else if (finalI >= 18 && finalI < 24) {
                            selectSeat(finalI - 18, "D", true);
                        } else {
                            selectSeat(finalI - 24, "E", true);
                        }
                        quantitySeat++;
                        setPrice();
                    } else {
                        System.out.println("Max seat");
                    }
                }
                tvQuantitySeat.setText(quantitySeat + "");
            });
        }

        btnFinishChooseSeat.setOnClickListener(v -> {
            if (seatSelected.size() > 0) {
                Intent intent = new Intent(ChooseSeatActivity.this, PaymentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("schedule", scheduleCurrent);
                bundle.putSerializable("cinema", cinemaCurrent);
                bundle.putSerializable("movie", movieCurrent);
                bundle.putSerializable("seat", (Serializable) seatSelected);
                bundle.putInt("quantitySeat", quantitySeat);
                bundle.putInt("totalPrice", quantitySeat * scheduleCurrent.getPrice());
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                System.out.println("Choose seat");
            }
        });
    }


    private void selectSeat(int number, String row, boolean choose) {
        if (choose) {
            seatSelected.add(row + (number + 1));
            System.out.println("Choose seat: " + row + (number + 1));
        } else {
            seatSelected.remove(row + (number + 1));
            System.out.println("UnChoose seat: " + row + (number + 1));
        }
        System.out.println("List seat: " + seatSelected);
        if (seatSelected.size() > 0) {
            tvListSeatSelected.setText(seatSelected.toString());
        } else {
            tvListSeatSelected.setText("");
        }
    }

    private void setPrice() {
        if (quantitySeat > 0) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String formattedString = formatter.format(quantitySeat * scheduleCurrent.getPrice());
            tvTotalPrice.setText(formattedString);
            btnFinishChooseSeat.setEnabled(true);
        } else {
            tvTotalPrice.setText("0đ");
            btnFinishChooseSeat.setEnabled(false);
        }
    }

    private void initApp() {
        movieService = MovieService.getInstanceMovieService();
        seatService = SeatService.getInstanceSeatService();
        seatSelected = new ArrayList<>();
        listSeatInSchedule = new ArrayList<>();
        getMovieCurrent();
        tvBackChooseMovie = findViewById(R.id.tvBackChooseSeat);
        tvListSeatSelected = findViewById(R.id.tvListSeatSelected);
        tvNameCinema = findViewById(R.id.tvNameCinema);
        tvTimeShowMovie = findViewById(R.id.tvTimeShowMovie);
        tvNameMovie = findViewById(R.id.tvNameMovie);
        tvQuantitySeat = findViewById(R.id.tvQuantitySeat);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnFinishChooseSeat = findViewById(R.id.btnFinishChooseSeat);
        listSeat = new ArrayList<>();
        tvSelected = findViewById(R.id.tvSelected);
        tvSold = findViewById(R.id.tvSold);

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
