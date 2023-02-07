package com.shreyxnsh.vtop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shreyxnsh.vtop.MainActivity;
import com.shreyxnsh.vtop.R;
import com.shreyxnsh.vtop.ui.aboutus.BranchAdapter;
import com.shreyxnsh.vtop.ui.aboutus.BranchModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    private int[] images;
    private String[] text;
    private SliderAdapter adapter;
    private SliderView sliderView;
    private ImageView map;
    private ViewPager viewPager;
    private BranchAdapter branchAdapter;
    private List<BranchModel> list;
    EventAdapter eventadapter;

    // variables for latest event rv
    RecyclerView eventRV;
    ArrayList<String> dataSource;
    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.aero_icon, "B.Tech Aerospace Engineering (BAS)"));
        list.add(new BranchModel(R.drawable.bio_icon, "B.Tech Bioengineering (BOE)"));

        list.add(new BranchModel(R.drawable.cse_icon, "B.Tech Computer Science & Engineering (BCE)"));
        list.add(new BranchModel(R.drawable.cse_icon, "B.Tech Computer Science & Engineering (Artificial Intelligence & Machine Learning) (BAI)"));
        list.add(new BranchModel(R.drawable.cse_icon, "B.Tech Computer Science & Engineering (Cyber Security & Digital Forensics) (BCY)"));
        list.add(new BranchModel(R.drawable.cse_icon, "B.Tech Computer Science & Engineering (Cloud Computing & Automation) (BSA)"));
        list.add(new BranchModel(R.drawable.cse_icon, "B.Tech Computer Science & Engineering (E-Commerce Technology) (BET)"));
        list.add(new BranchModel(R.drawable.cse_icon, "B.Tech Computer Science & Engineering (Education Technology) (BET)"));
        list.add(new BranchModel(R.drawable.cse_icon, "B.Tech Computer Science & Engineering (Gaming Technology) (BCG)"));
        list.add(new BranchModel(R.drawable.cse_icon, "B.Tech Computer Science & Engineering (Health Informatics) (BHI)"));

        list.add(new BranchModel(R.drawable.ece_icon, "B.Tech Electronics & Communication Engineering (BEC)"));
        list.add(new BranchModel(R.drawable.ece_icon, "B.Tech Electronics & Communication Engineering (Artificial Intelligence & Cybernetics) (BEC)"));

        list.add(new BranchModel(R.drawable.mech_icon, "B.Tech Mechanical Engineering (BME)"));
        list.add(new BranchModel(R.drawable.mech_icon, "B.Tech Mechanical Engineering (Artificial Intelligence & Robotics) (BMR)"));

        // adapter
        branchAdapter = new BranchAdapter(getContext(), list);
        // viewpager initialisation
        viewPager = view.findViewById(R.id.viewpager);

        //setting adapter in viewpager
        viewPager.setAdapter(branchAdapter);

        eventRV = view.findViewById(R.id.eventsRV);
        eventsRVData();

        return view;

    }

    private void eventsRVData() {
        eventRV.setHasFixedSize(true);
        eventRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        ArrayList<EventData> eventsList = new ArrayList<>();
        eventsList.add(new EventData(R.drawable.facultydemo, "Vivaan", "09 Feb @ 10am", "Auditorium", "Insights Club"));
        eventsList.add(new EventData(R.drawable.ic_githublogo, "Aadhav", "10 Feb @ 10am", "Auditorium", "AI Club"));

        eventadapter = new EventAdapter(eventsList);
        eventRV.setAdapter(eventadapter);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sliderView = (SliderView) getView().findViewById(R.id.sliderView);
        //setting images and text
        images = new int[]{
                R.drawable.sv1,
                R.drawable.vit_maingate,
                R.drawable.classroom_vitb,
                R.drawable.sv3,
                R.drawable.sv2

        };
        text = new String[]{
                "Vellore Institute of Technology, Bhopal",
                "VIT-B Entrance",
                "Classrooms at VIT-B",
                "Students at VIT-B" ,
                "Boys Hostel"
        };

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