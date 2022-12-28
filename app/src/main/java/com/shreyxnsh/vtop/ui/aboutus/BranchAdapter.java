package com.shreyxnsh.vtop.ui.aboutus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.shreyxnsh.vtop.R;

import java.util.List;

public class BranchAdapter extends PagerAdapter {
    private Context context;
    private List<BranchModel> list;


    public BranchAdapter(Context context, List<BranchModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.branch_item,container,false);
        ImageView br_icon;
        TextView br_title;

        br_icon = view.findViewById(R.id.br_icon);
        br_title = view.findViewById(R.id.br_title);

        br_icon.setImageResource(list.get(position).getImg());
        br_title.setText(list.get(position).getTitle());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
