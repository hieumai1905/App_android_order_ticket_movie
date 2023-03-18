package com.example.btl_android_apporderticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_android_apporderticket.model.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private final List<Photo> listPhotos;
    private final Context context;
    private static int resourceItem, resourceImage;

    public PhotoAdapter(Context context, List<Photo> list, int resourceItem, int resourceImage) {
        this.context = context;
        listPhotos = list;
        PhotoAdapter.resourceItem = resourceItem;
        PhotoAdapter.resourceImage = resourceImage;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourceItem, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = listPhotos.get(position);
        if (photo != null) {
            Glide.with(context).load(photo.getUrl()).centerCrop().into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (listPhotos != null) {
            return listPhotos.size();
        }
        return 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(resourceImage);
        }
    }

}
