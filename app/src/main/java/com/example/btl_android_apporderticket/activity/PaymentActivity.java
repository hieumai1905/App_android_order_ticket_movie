package com.example.btl_android_apporderticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.adapter.MovieSeatAdapter;
import com.example.btl_android_apporderticket.handle.event.ShowDialog;
import com.example.btl_android_apporderticket.handle.getdata.DataBuffer;
import com.example.btl_android_apporderticket.handle.getdata.HandleTime;
import com.example.btl_android_apporderticket.handle.mycallback.ICallbackEventClick;
import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Cinema;
import com.example.btl_android_apporderticket.model.Invoice;
import com.example.btl_android_apporderticket.model.Movie;
import com.example.btl_android_apporderticket.model.Schedule;
import com.example.btl_android_apporderticket.service.invoice.IInvoiceService;
import com.example.btl_android_apporderticket.service.invoice.InvoiceService;
import com.example.btl_android_apporderticket.service.seat.SeatService;
import com.example.btl_android_apporderticket.viewmodel.MovieTicket;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okio.Buffer;

public class PaymentActivity extends Activity {

    private TextView tvBackPayment;
    private TextView tvNameMovie;
    private TextView tvAgeRating;
    private TextView tvAddressCinema;
    private TextView tvCalendar;
    private TextView tvNumberTicket;
    private TextView tvTotalPrice;
    private ImageView imgMovieBooking;
    private ListView lvItemTicket;
    private Button btnOrder;
    private RadioGroup radioGroup;
    private RadioButton rbCash, rbBanking;
    private int totalPrice;
    private IInvoiceService invoiceService;
    private Invoice invoice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initApp();
        loadData();
    }

    private void loadData() {
        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("movie");
        Schedule schedule = (Schedule) intent.getSerializableExtra("schedule");
        Cinema cinema = (Cinema) intent.getSerializableExtra("cinema");
        List<String> listSeat = (List<String>) intent.getSerializableExtra("seat");
        totalPrice = intent.getIntExtra("totalPrice", 0);
        int quantity = intent.getIntExtra("quantitySeat", 0);
        tvNameMovie.setText(movie.getTitle());
        tvAgeRating.setText("C " + movie.getAgeRating() + " - No Children Under " + movie.getAgeRating() + " Years Old");
        tvAddressCinema.setText(cinema.getAddressCinema());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(schedule.getShowTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("vi", "VN"));
        tvCalendar.setText(outputFormat.format(date) + " Begin at: " + HandleTime.getHourAndMinute(schedule.getShowTime()) + "-" + HandleTime.getHourAndMinute(schedule.getEndTime()));
        tvNumberTicket.setText(" + " + quantity + " Ticket");
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedString = formatter.format(totalPrice);
        tvTotalPrice.setText(formattedString);
        Glide.with(this).load(movie.getPoster()).into(imgMovieBooking);
        List<MovieTicket> listMovieTicket = new ArrayList<>();
        for (String seat : listSeat) {
            listMovieTicket.add(new MovieTicket(movie.getTitle(), "+" + seat));
        }
        MovieSeatAdapter movieSeatAdapter = new MovieSeatAdapter(listMovieTicket);
        lvItemTicket.setAdapter(movieSeatAdapter);
    }

    private void initApp() {
        invoiceService = InvoiceService.getInstanceInvoiceService();
        tvBackPayment = findViewById(R.id.tvBack);
        tvNameMovie = findViewById(R.id.tvTitleMovie);
        tvAgeRating = findViewById(R.id.tvAgeRatingMovie);
        tvAddressCinema = findViewById(R.id.tvAddressCinema);
        tvCalendar = findViewById(R.id.tvDateMovie);
        imgMovieBooking = findViewById(R.id.imgMovieBooking);
        tvNumberTicket = findViewById(R.id.tvNumberTicket);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnOrder = findViewById(R.id.btnBookTicket);
        lvItemTicket = findViewById(R.id.lvItem);
        radioGroup = findViewById(R.id.radioGroup);
        rbCash = findViewById(R.id.rbCash);
        rbBanking = findViewById(R.id.rbBanking);
        tvBackPayment.setOnClickListener(v -> {
            finish();
        });

        btnOrder.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            String methodPayment = "";
            if (selectedId != -1) {
                System.out.println("Payment method: " + methodPayment);
                RadioButton selectedRadioButton = findViewById(selectedId);
                methodPayment = selectedRadioButton.getText().toString();
                payment(methodPayment);
            } else {
                ShowDialog.show(this, "Notification", "Please choose method payment!", "OK", "", new ICallbackEventClick() {
                    @Override
                    public void onSelectObject(Object o) {

                    }
                });
            }
        });
    }

    private void payment(String methodPayment) {
        String status = "Success";
        String userId = DataBuffer.ID_USER_CURRENT;
        invoice = new Invoice(totalPrice, methodPayment, status, userId);
        try {
            invoiceService.add(invoice, new IServiceCallback<Invoice>() {
                @Override
                public void onDataReceived(Invoice data) {
                    invoice = data;
                    System.out.println("Payment success: " + invoice.toString());
                    updateSeat();
                }

                @Override
                public void onRequestFailed(Throwable t) {
                    System.out.println("Payment failed");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSeat() {
        Intent intent = getIntent();
        List<String> listSeat = (List<String>) intent.getSerializableExtra("seat");
        Schedule schedule = (Schedule) intent.getSerializableExtra("schedule");
        List<Integer> rowNumberSeat = new ArrayList<>();
        for (String seat : listSeat) {
            if (seat.charAt(0) == 'A') {
                rowNumberSeat.add(Integer.parseInt(seat.charAt(1) + ""));
            } else if (seat.charAt(0) == 'B') {
                rowNumberSeat.add(Integer.parseInt(seat.charAt(1) + "") + 6);
            } else if (seat.charAt(0) == 'C') {
                rowNumberSeat.add(Integer.parseInt(seat.charAt(1) + "") + 12);
            } else if (seat.charAt(0) == 'D') {
                rowNumberSeat.add(Integer.parseInt(seat.charAt(1) + "") + 18);
            } else if (seat.charAt(0) == 'E') {
                rowNumberSeat.add(Integer.parseInt(seat.charAt(1) + "") + 24);
            }
        }
        System.out.println(rowNumberSeat);

        try {
            for (int i = 0; i < rowNumberSeat.size(); i++) {
                int finalI = i;
                SeatService.getInstanceSeatService().updateSeatOfSchedule(schedule.getScheduleId(), String.valueOf(rowNumberSeat.get(i)), new IServiceCallback<Boolean>() {
                    @Override
                    public void onDataReceived(Boolean data) {
                        System.out.println("Update seat success");
                        if (finalI == rowNumberSeat.size() - 1) {
                            ShowDialog.show(PaymentActivity.this, "Notification", "Order successfully", "OK", "Back to home", new ICallbackEventClick() {
                                @Override
                                public void onSelectObject(Object o) {
                                    Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                                    DataBuffer.STATUS_APP = 1;
                                    startActivity(intent);
                                }
                            });
                        }
                    }

                    @Override
                    public void onRequestFailed(Throwable t) {
                        System.out.println("Update seat failed");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
