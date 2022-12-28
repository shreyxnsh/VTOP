package com.shreyxnsh.vtop.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyxnsh.vtop.R;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

    private RecyclerView cs, mech, eee, asl;
    private LinearLayout csNoData, mechNoData, eeeNoData, aslNoData;
    private List<FacultyData> list1, list2, list3, list4;
    private FacultyAdapter adapter;

    private DatabaseReference reference, dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_faculty, container, false);



        cs = view.findViewById(R.id.csDepartment);
        mech = view.findViewById(R.id.mechDepartment);
        eee = view.findViewById(R.id.eeeDepartment);
        asl = view.findViewById(R.id.aslDepartment);

        csNoData = view.findViewById(R.id.csNoData);
        mechNoData = view.findViewById(R.id.mechNoData);
        aslNoData = view.findViewById(R.id.aslNoData);
        eeeNoData = view.findViewById(R.id.eeeNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Faculty");
        csDepartment();
        mechDepartment();
        eeeDepartment();
        aslDepartment();

        return view;

    }

    private void csDepartment() {
        dbRef = reference.child("SCSE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();
                if(!snapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    cs.setVisibility(View.GONE);
                }else{
                    csNoData.setVisibility(View.GONE);
                    cs.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list1.add(data);
                    }
                    cs.setHasFixedSize(true);
                    cs.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(list1, getContext());
                    cs.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mechDepartment() {
        dbRef = reference.child("SMEC");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if(!snapshot.exists()){
                    mechNoData.setVisibility(View.VISIBLE);
                    mech.setVisibility(View.GONE);
                }else{
                    mechNoData.setVisibility(View.GONE);
                    mech.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list2.add(data);
                    }
                    mech.setHasFixedSize(true);
                    mech.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(list2, getContext());
                    mech.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eeeDepartment() {
        dbRef = reference.child("SEEE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if(!snapshot.exists()){
                    eeeNoData.setVisibility(View.VISIBLE);
                    eee.setVisibility(View.GONE);
                }else{
                    eeeNoData.setVisibility(View.GONE);
                    eee.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list3.add(data);
                    }
                    eee.setHasFixedSize(true);
                    eee.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(list3, getContext());
                    eee.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void aslDepartment() {
        dbRef = reference.child("SASL");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if(!snapshot.exists()){
                    aslNoData.setVisibility(View.VISIBLE);
                    asl.setVisibility(View.GONE);
                }else{
                    aslNoData.setVisibility(View.GONE);
                    asl.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list4.add(data);
                    }
                    asl.setHasFixedSize(true);
                    asl.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(list4, getContext());
                    asl.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}