package com.shreyxnsh.vtop.ui.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;
import com.shreyxnsh.vtop.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewAdapter> {
    private List<EventData> list;
    private Context context;


    public EventAdapter(List<EventData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout inflate in onCreate
        View view = LayoutInflater.from(context).inflate(R.layout.event_card, parent, false);
        return new EventViewAdapter(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onBindViewHolder(EventViewAdapter holder, int position) {
        EventData item = list.get(position);
        holder.eventname.setText(item.getEventname());
        holder.datetime.setText(item.getDatetime());
        holder.venue.setText(item.getVenue());
        holder.clubname.setText(item.getClubname());
        try {
//            Picasso.get().load(item.getImage()).into(holder.image);
            Glide.with(context)
                    .load(item.getImage())
                    .override(250,250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class EventViewAdapter extends RecyclerView.ViewHolder {

        private TextView eventname, datetime, venue, clubname;
        private ImageView image;
        private MaterialCardView eventCard;


        public EventViewAdapter(@NonNull View itemView) {
            super(itemView);
            eventname = itemView.findViewById(R.id.eventTV);
            datetime = itemView.findViewById(R.id.timeTV);
            venue = itemView.findViewById(R.id.locationTV);
            clubname = itemView.findViewById(R.id.hostTV);
            image = itemView.findViewById(R.id.clublogoIV);

            eventCard = itemView.findViewById(R.id.eventCard);
        }
    }
}
