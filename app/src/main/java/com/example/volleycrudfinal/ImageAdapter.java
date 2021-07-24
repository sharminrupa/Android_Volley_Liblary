package com.example.volleycrudfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageModelViewHolder> {

    private Context context;
    private List<ImageModel> imageList;

    public ImageAdapter(Context context , List<ImageModel> imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    @NonNull
    @Override
    public ImageModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_show_design, parent,false);
        ImageModelViewHolder viewHolder = new ImageModelViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageModelViewHolder holder , int position) {
        Glide.with(context).load(imageList.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}

class ImageModelViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;

    public ImageModelViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgView);
    }
}
