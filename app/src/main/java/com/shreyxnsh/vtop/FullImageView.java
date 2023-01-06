package com.shreyxnsh.vtop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

public class FullImageView extends AppCompatActivity {

    private ZoomageView zoomImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        zoomImage =  findViewById(R.id.zoomImage);

        String image = getIntent().getStringExtra("image");

        Picasso.get().load(image).into(zoomImage);
    }
}