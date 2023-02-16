package com.shreyxnsh.vtop.ui.clubs;

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
import com.shreyxnsh.vtop.ebook.EbookData;
import com.shreyxnsh.vtop.ui.events.EventData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewAdapter> {
    private List<ClubData> list;
    private Context context;


    public ClubAdapter(Context context,List<ClubData> list) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public ClubViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout inflate in onCreate
        View view = LayoutInflater.from(context).inflate(R.layout.club_item_layout, parent, false);
        return new ClubViewAdapter(view);
    }

    public void FilteredList(ArrayList<ClubData> filterList) {
        list = filterList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onBindViewHolder(ClubViewAdapter holder, int position) {
        ClubData item = list.get(position);
        holder.facultyname.setText(item.getFacultyname());
        holder.studentname.setText(item.getStudentname());
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

    public class ClubViewAdapter extends RecyclerView.ViewHolder {

        private TextView clubname, facultyname, studentname;
        private ImageView image;
        private MaterialCardView clubCard;


        public ClubViewAdapter(@NonNull View itemView) {
            super(itemView);
            studentname = itemView.findViewById(R.id.studentName);
            facultyname = itemView.findViewById(R.id.facultyName);
            clubname = itemView.findViewById(R.id.clubName);
            image = itemView.findViewById(R.id.clubImage);

            clubCard = itemView.findViewById(R.id.clubCard);
        }
    }
}
