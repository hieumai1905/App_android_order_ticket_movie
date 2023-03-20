package com.example.btl_android_apporderticket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.hashcode.HashCodeToLong;
import com.example.btl_android_apporderticket.model.Cinema;

import java.util.List;

public class CinemaAdapter extends BaseAdapter {
    private List<Cinema> listCinemas;
    private Context context;

    public CinemaAdapter(Context context, List<Cinema> listCinemas) {
        this.listCinemas = listCinemas;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (listCinemas != null) return listCinemas.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return listCinemas.get(i);
    }

    @Override
    public long getItemId(int i) {
        String id = listCinemas.get(i).getIdCinema();
        return HashCodeToLong.getHashedId(id);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCinema = view;
        if (view == null) {
            viewCinema = View.inflate(viewGroup.getContext(), R.layout.item_cinema, null);
        }
        Cinema cinema = listCinemas.get(i);
        ((TextView) viewCinema.findViewById(R.id.tvNameCinema)).setText(cinema.getNameCinema());
        ((TextView) viewCinema.findViewById(R.id.tvAddressCinema)).setText(cinema.getAddressCinema());
        ImageView imageView = viewCinema.findViewById(R.id.imgCinema);
        Glide.with(context).load(cinema.getImageCinema()).centerCrop().into(imageView);
        return viewCinema;
    }
}
