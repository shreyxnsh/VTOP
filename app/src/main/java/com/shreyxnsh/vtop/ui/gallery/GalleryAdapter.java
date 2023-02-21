package com.shreyxnsh.vtop.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shreyxnsh.vtop.FullImageView;
import com.shreyxnsh.vtop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewAdapter> {

    private Context context;
    private List<String> images;

    public GalleryAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public GalleryViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating view in onCreate function
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_image,parent,false);
        return new GalleryViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewAdapter holder, int position) {

        Picasso.get().load(images.get(position)).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageView.class);
                intent.putExtra("image",images.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return images.size();
    }

    public class GalleryViewAdapter extends RecyclerView.ViewHolder {

        //finding images
        ImageView imageView;

        public GalleryViewAdapter(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
        }
    }
}
