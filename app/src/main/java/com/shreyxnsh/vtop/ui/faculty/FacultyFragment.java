package com.shreyxnsh.vtop.ui.faculty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyxnsh.vtop.MainActivity;
import com.shreyxnsh.vtop.R;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

    private RecyclerView facultyRV;
    private LinearLayout nodata;
    private List<FacultyData> facultyList;
    private FacultyAdapter adapter;
    private EditText faculty_search;
    private LottieAnimationView progressBar;

    private DatabaseReference reference, dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_faculty, container, false);


        facultyRV = view.findViewById(R.id.facultyListRV);

        nodata = view.findViewById(R.id.NoData);

        progressBar = view.findViewById(R.id.progressBar);

        faculty_search = view.findViewById(R.id.faculty_search);

        reference = FirebaseDatabase.getInstance().getReference().child("Faculty");
        csDepartment();
        mechDepartment();
        eeeDepartment();
        aslDepartment();
//        addDataToList();

        return view;

    }


    private void csDepartment() {
        dbRef = reference.child("SCSE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list = new ArrayList<>();
                facultyList = new ArrayList<>();
                if(!snapshot.exists()){
                    nodata.setVisibility(View.VISIBLE);
                    facultyRV.setVisibility(View.GONE);
                }else{
                    nodata.setVisibility(View.GONE);
                    facultyRV.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        facultyList.add(0,data);
//                        list.addAll(cseList);
                    }
                    facultyRV.setHasFixedSize(true);
                    facultyRV.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(facultyList, getContext());
                    facultyRV.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
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
                if(!snapshot.exists()){
                    nodata.setVisibility(View.VISIBLE);
                    facultyRV.setVisibility(View.GONE);
                }else{
                    nodata.setVisibility(View.GONE);
                    facultyRV.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        facultyList.add(0,data);
//                        list.addAll(mecList);

                    }
                    facultyRV.setHasFixedSize(true);
                    facultyRV.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(facultyList, getContext());
                    facultyRV.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
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
                if(!snapshot.exists()){
                    nodata.setVisibility(View.VISIBLE);
                    facultyRV.setVisibility(View.GONE);
                }else{
                    nodata.setVisibility(View.GONE);
                    facultyRV.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        facultyList.add(0,data);
//                        list.addAll(eeeList);
                        progressBar.setVisibility(View.GONE);

                    }
                    facultyRV.setHasFixedSize(true);
                    facultyRV.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(facultyList, getContext());
                    facultyRV.setAdapter(adapter);
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
                if(!snapshot.exists()){
                    nodata.setVisibility(View.VISIBLE);
                    facultyRV.setVisibility(View.GONE);
                }else{
                    nodata.setVisibility(View.GONE);
                    facultyRV.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        facultyList.add(0,data);
//                        list.addAll(aslList);

                    }
                    facultyRV.setHasFixedSize(true);
                    facultyRV.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(facultyList, getContext());
                    facultyRV.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // searching pdf in edit text view
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
    private void filter(String text){
        ArrayList<FacultyData> filterList = new ArrayList<>();
        for (FacultyData item : facultyList){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }

        }
        if (filterList.isEmpty()){
            Toast.makeText(getContext(), "No data Found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.FilteredList(filterList);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Faculty Info");
    }


}