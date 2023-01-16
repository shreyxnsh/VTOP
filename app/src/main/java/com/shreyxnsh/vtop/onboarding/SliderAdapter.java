package com.shreyxnsh.vtop.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.shreyxnsh.vtop.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    // creating arrays of the images,titles and descriptions of the slides in the onBoarding screens
    int images[] = {
            R.drawable.sl1,
            R.drawable.sl2,
            R.drawable.sl3,
            R.drawable.sl4
    };

    int headings[] = {
            R.string.heading1,
            R.string.heading2,
            R.string.heading3,
            R.string.heading4
    };

    int subheadings[] = {
            R.string.subhd1,
            R.string.subhd2,
            R.string.subhd3,
            R.string.subhd4
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //inflating slider_layout
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        // Views from the design
        ImageView sliderImage = view.findViewById(R.id.slider_image);
        TextView sliderHeading = view.findViewById(R.id.slider_heading);
        TextView sliderSubheading = view.findViewById(R.id.slider_subheading);

        //setting the values
        sliderImage.setImageResource(images[position]);
        sliderHeading.setText(headings[position]);
        sliderSubheading.setText(subheadings[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
