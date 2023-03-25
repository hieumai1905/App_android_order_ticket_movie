package com.example.btl_android_apporderticket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.getdata.DataBuffer;
import com.example.btl_android_apporderticket.handle.mycallback.ICallbackEventClickMovie;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {

    private List<String> timeShowMovies;
    private ICallbackEventClickMovie callbackEventClickMovie;

    public void setData(List<String> list, ICallbackEventClickMovie callbackEventClickMovie) {
        this.timeShowMovies = list;
        this.callbackEventClickMovie = callbackEventClickMovie;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time, parent, false);
        return new TimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeViewHolder holder, int position) {
        String time = timeShowMovies.get(position);
        if (time != null) {
            holder.btnTime.setText(time);
            holder.btnTime.setOnClickListener(v -> {
                DataBuffer.TIME_CURRENT = time;
                callbackEventClickMovie.onSelectMovie(time);
            });
        }
    }

    @Override
    public int getItemCount() {
        if (timeShowMovies != null)
            return timeShowMovies.size();
        return 0;
    }


    public class TimeViewHolder extends RecyclerView.ViewHolder {
        private Button btnTime;

        public TimeViewHolder(@NonNull View itemView) {
            super(itemView);
            btnTime = itemView.findViewById(R.id.btnTime);
        }
    }
}
