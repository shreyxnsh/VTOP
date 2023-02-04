package com.shreyxnsh.vtop.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shreyxnsh.vtop.MainActivity;
import com.shreyxnsh.vtop.R;
import com.shreyxnsh.vtop.ui.home.HomeFragment;

public class OnBoardingScreen extends AppCompatActivity {

    //variables acc to xml
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    MaterialButton getStartedBtn;
    FloatingActionButton nextBtn;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
        setContentView(R.layout.activity_on_boarding_screen);

        //initialising variables
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        getStartedBtn = findViewById(R.id.getStartedBtn);
        nextBtn = findViewById(R.id.nextBtn);

        sliderAdapter = new SliderAdapter(this);
        animation = AnimationUtils.loadAnimation(this,R.anim.side_anim);

        //setting adapter in viewpager for onboarding
        viewPager.setAdapter(sliderAdapter);
        //default dot position
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }


    // next button
    public void next(View view){
        viewPager.setCurrentItem(currentPos+1);
    }


    private void addDots(int position) {
        //creating dots
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.bright_blue));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            //setting button visibilities acc to position
            if (position == 0) {
                nextBtn.setVisibility(View.VISIBLE);
                getStartedBtn.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                nextBtn.setVisibility(View.VISIBLE);
                getStartedBtn.setVisibility(View.INVISIBLE);
            } else if (position == 2) {
                nextBtn.setVisibility(View.VISIBLE);
                getStartedBtn.setVisibility(View.INVISIBLE);
            } else {
                getStartedBtn.setAnimation(animation);
                getStartedBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}