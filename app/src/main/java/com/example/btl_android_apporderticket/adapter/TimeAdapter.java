package com.example.btl_android_apporderticket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.handle.getdata.DataBuffer;
import com.example.btl_android_apporderticket.handle.getdata.HandleTime;
import com.example.btl_android_apporderticket.handle.mycallback.ICallbackEventClick;
import com.example.btl_android_apporderticket.model.Schedule;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {

    private List<Schedule> scheduleList;
    private ICallbackEventClick callbackEventClick;

    public void setData(List<Schedule> scheduleList, ICallbackEventClick callbackEventClickMovie) {
        this.scheduleList = scheduleList;
        this.callbackEventClick = callbackEventClickMovie;
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
        String time = HandleTime.getHourAndMinute(scheduleList.get(position).getShowTime());
        if (time != null) {
            holder.btnTime.setText(time);
            holder.btnTime.setOnClickListener(v -> {
                DataBuffer.ID_SCHEDULER_CURRENT = time;
                DataBuffer.scheduleCurrent = scheduleList.get(position);
                callbackEventClick.onSelectObject(scheduleList.get(position).getScheduleId());
            });
        }
    }

    @Override
    public int getItemCount() {
        if (scheduleList != null)
            return scheduleList.size();
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
