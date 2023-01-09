package com.shreyxnsh.vtop.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shreyxnsh.vtop.MainActivity;
import com.shreyxnsh.vtop.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class HomeFragment extends Fragment {

    private int[] images;
    private String[] text;
    private SliderAdapter adapter;
    private SliderView sliderView;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sliderView = (SliderView) getView().findViewById(R.id.sliderView);
        //setting images and text
        images = new int[]{R.drawable.sv1, R.drawable.sv2,R.drawable.sv3};
        text = new String[]{"Vellore Institute of Technology, Bhopal", "Boys Hostel", "Students at VIT-B"};

        // creating adapter
        adapter = new SliderAdapter(images, text);
        // setting adapter in slider view
        sliderView.setSliderAdapter(adapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderView.startAutoCycle();

        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q= Vellore Institute of Technology, Bhopal ");
        // function to open google maps to navigate to college
        // using intent to open map
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //which package to open in the intent?
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Dashboard");
    }
}