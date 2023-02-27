package com.shreyxnsh.vtop.ui.faculty;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;

import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shreyxnsh.vtop.MainActivity;
import com.shreyxnsh.vtop.R;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {

    private RecyclerView facultyRV;
    private List<FacultyData> facultyList;
    private FacultyAdapter adapter;
    private EditText faculty_search;
    private LottieAnimationView progressBar;

    private DatabaseReference reference;

    LinearLayoutManager layoutManager;
    final int limit = 10;
    int start = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);


        facultyRV = view.findViewById(R.id.facultyListRV);
        layoutManager = new LinearLayoutManager(getContext());
        progressBar = view.findViewById(R.id.progressBar);

        faculty_search = view.findViewById(R.id.faculty_search);

        reference = FirebaseDatabase.getInstance().getReference().child("Faculty");
        reference.keepSynced(true);

        Query facultyQuery = reference.limitToFirst(limit).startAt(start);
        facultyQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                getFacultyData();
                start += (limit+1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    private void getFacultyData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        facultyList = new ArrayList<>();
                        if (!snapshot.exists()) {
                            facultyRV.setVisibility(View.GONE);
                        } else {
                            facultyRV.setVisibility(View.VISIBLE);
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                FacultyData data = dataSnapshot.getValue(FacultyData.class);
                                facultyList.add(0, data);
                            }
                                    facultyRV.setHasFixedSize(true);
                                    facultyRV.setLayoutManager(layoutManager);
                                    adapter = new FacultyAdapter(facultyList, getContext());
                                    facultyRV.setAdapter(adapter);
                                    progressBar.setVisibility(View.GONE);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                faculty_search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        filter(editable.toString());
                    }
                });
            }
        }).start();
    }




    private void filter(String text) {
        ArrayList<FacultyData> filterList = new ArrayList<>();
        for (FacultyData item : facultyList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }

        }
        if (filterList.isEmpty()) {
            Toast.makeText(getContext(), "No data Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.FilteredList(filterList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Faculty Info");
    }


}