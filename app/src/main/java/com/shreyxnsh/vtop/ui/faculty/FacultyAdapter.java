package com.shreyxnsh.vtop.ui.faculty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyxnsh.vtop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewAdapter> {
    private List<FacultyData> list;
    private Context context;

    public FacultyAdapter(List<FacultyData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FacultyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout inflate in onCreate
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout, parent, false);
        return new FacultyViewAdapter(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewAdapter holder, int position) {
        FacultyData item = list.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.designation.setText(item.getDesignation());
        holder.cabin.setText(item.getCabin());
        try {
            Picasso.get().load(item.getImage()).into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class FacultyViewAdapter extends RecyclerView.ViewHolder {

        private TextView name, email, designation, cabin;
        private ImageView image;


        public FacultyViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.facultyName);
            email = itemView.findViewById(R.id.facultyEmail);
            designation = itemView.findViewById(R.id.facultyDesignation);
            image = itemView.findViewById(R.id.facultyImage);
            cabin = itemView.findViewById(R.id.facultyCabin);
        }
    }
}
