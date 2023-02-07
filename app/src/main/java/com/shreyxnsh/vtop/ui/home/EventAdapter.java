package com.shreyxnsh.vtop.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyxnsh.vtop.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    ArrayList<EventData> eventsList;

    public EventAdapter(ArrayList<EventData> eventsList) {
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventData eventData = eventsList.get(position);
        holder.image.setImageResource(eventData.getImage());
        holder.name.setText(eventData.getName());
        holder.time.setText(eventData.getTime());
        holder.location.setText(eventData.getLocation());
        holder.clubname.setText(eventData.getClubname());

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,time,location, clubname;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.clublogo);
            name = itemView.findViewById(R.id.eventTV);
            time = itemView.findViewById(R.id.timeTV);
            location = itemView.findViewById(R.id.locationTV);
            clubname = itemView.findViewById(R.id.clubTV);
        }
    }
}
