package com.shreyxnsh.vtop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Keep;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyxnsh.vtop.MainActivity;
import com.shreyxnsh.vtop.R;
import com.shreyxnsh.vtop.ui.aboutus.BranchAdapter;
import com.shreyxnsh.vtop.ui.aboutus.BranchModel;
import com.shreyxnsh.vtop.ui.events.EventAdapter;
import com.shreyxnsh.vtop.ui.events.EventData;
import com.shreyxnsh.vtop.ui.faculty.FacultyAdapter;
import com.shreyxnsh.vtop.ui.faculty.FacultyData;
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

    // latest event variables
    EventAdapter eventAdapter;
    private DatabaseReference reference;
    RecyclerView eventRV;
    private List<EventData> eventDataList;


    @Override
    @Keep
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        list = new ArrayList<>();


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
        list.add(new BranchModel(R.drawable.aero_icon, "B.Tech Aerospace Engineering (BAS)"));
        list.add(new BranchModel(R.drawable.bio_icon, "B.Tech Bioengineering (BOE)"));

        // adapter
        branchAdapter = new BranchAdapter(getContext(), list);
        // viewpager initialisation
        viewPager = view.findViewById(R.id.viewpager);

        //setting adapter in viewpager
        viewPager.setAdapter(branchAdapter);

        eventRV = view.findViewById(R.id.eventsRV);
        eventRV.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        reference = FirebaseDatabase.getInstance().getReference().child("Events");
        reference.keepSynced(true);
        getEventData();

        return view;

    }

    private void getEventData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list = new ArrayList<>();
                eventDataList = new ArrayList<>();
                if(!snapshot.exists()){
                    eventRV.setVisibility(View.GONE);
                }else{
                    eventRV.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        EventData data = dataSnapshot.getValue(EventData.class);
                        eventDataList.add(0,data);
//                        list.addAll(cseList);
                    }
                    eventRV.setHasFixedSize(true);
                    eventRV.setLayoutManager(new LinearLayoutManager(getContext()));
                    eventAdapter = new EventAdapter(eventDataList, getContext());
                    eventRV.setAdapter(eventAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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