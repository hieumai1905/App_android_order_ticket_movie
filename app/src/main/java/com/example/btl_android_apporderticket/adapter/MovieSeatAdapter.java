package com.example.btl_android_apporderticket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.viewmodel.MovieTicket;

import java.util.List;

public class MovieSeatAdapter extends BaseAdapter {
    private List<MovieTicket> listMovieTicket;

    public MovieSeatAdapter(List<MovieTicket> list) {
        this.listMovieTicket = list;
    }

    @Override
    public int getCount() {
        if (listMovieTicket != null)
            return listMovieTicket.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return listMovieTicket.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listMovieTicket.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewMovieTicket = view;
        if (viewMovieTicket == null) {
            viewMovieTicket = View.inflate(viewGroup.getContext(), R.layout.item_ticket, null);
        }
        ((TextView) viewMovieTicket.findViewById(R.id.tvNameMovie)).setText(listMovieTicket.get(i).getNameMovie());
        ((TextView) viewMovieTicket.findViewById(R.id.tvSeat)).setText(listMovieTicket.get(i).getSeatName());
        return viewMovieTicket;
    }
}
