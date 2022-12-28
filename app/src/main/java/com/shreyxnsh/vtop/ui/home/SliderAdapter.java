package com.shreyxnsh.vtop.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shreyxnsh.vtop.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {

    //images and text variables to store the attributes
    private int[] images;
    private String[] text;

    // creating a constructor is necessary


    public SliderAdapter(int[] images, String[] text) {
        this.images = images;
        this.text = text;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        // always inflate the layout in onCreate
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliter_item_layout,null);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        // setting all the views in the holder
        viewHolder.imageView.setImageResource(images[position]);
        viewHolder.textView.setText(text[position]);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder{

        // construct these classes and methods using alt + enter
        // variables according to the xml file
        private ImageView imageView;
        private TextView textView;

        public SliderViewHolder(View itemView) {

            super(itemView);
            // setting all the views into the variables
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textDescription);

        }
    }
}
