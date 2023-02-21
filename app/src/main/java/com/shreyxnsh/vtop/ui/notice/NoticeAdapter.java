package com.shreyxnsh.vtop.ui.notice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shreyxnsh.vtop.FullImageView;
import com.shreyxnsh.vtop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {

    // first step is initialising context and arraylist and creating the constructor for NoticeAdapter
    // second implement all the three methods along with extending NoticeViewAdapter with ViewHolder
    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating the layout in OnCreate method
        View view = LayoutInflater.from(context).inflate(R.layout.notices_item_layout, parent, false);
        // pass the variable in return
        return new NoticeViewAdapter(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, @SuppressLint("RecyclerView") int position) {
        //getting data from NoticeData class in currentItem variable in onBind method
        position = holder.getAdapterPosition();
        NoticeData currentItem = list.get(position);
        //setting title of notice
        holder.noticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());
        //setting notice image via Picasso and surrounding by try catch to handle exceptions
        try {
            if (currentItem.getImage() != null)
                Glide.with(context)
                        .load(currentItem.getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.noticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.noticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageView.class);
                intent.putExtra("image", currentItem.getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        // always return size() in getItemCount methods
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {

        //initialising all the itemView type buttons/textviews in NoticeViewAdapter class only

        private TextView noticeTitle, date, time;
        private ImageView noticeImage;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            //finding all variables acc to ids

            noticeTitle = itemView.findViewById(R.id.noticeTitle);
            noticeImage = itemView.findViewById(R.id.noticeImage);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}
