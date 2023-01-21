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

    private RecyclerView cs, mech, eee, asl;
    private LinearLayout csNoData, mechNoData, eeeNoData, aslNoData;
    private List<FacultyData> cseList, mecList, eeeList, aslList;
    private List<FacultyData> list;
    private FacultyAdapter adapter;
    private EditText faculty_search;
    private LottieAnimationView progressBar;

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
                cseList = new ArrayList<>();
                if(!snapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    cs.setVisibility(View.GONE);
                }else{
                    csNoData.setVisibility(View.GONE);
                    cs.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        cseList.add(0,data);
//                        list.addAll(cseList);
                    }
                    cs.setHasFixedSize(true);
                    cs.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(cseList, getContext());
                    cs.setAdapter(adapter);
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
//                list = new ArrayList<>();
                mecList = new ArrayList<>();
                if(!snapshot.exists()){
                    mechNoData.setVisibility(View.VISIBLE);
                    mech.setVisibility(View.GONE);
                }else{
                    mechNoData.setVisibility(View.GONE);
                    mech.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        mecList.add(0,data);
//                        list.addAll(mecList);

                    }
                    mech.setHasFixedSize(true);
                    mech.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(mecList, getContext());
                    mech.setAdapter(adapter);
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
//                list = new ArrayList<>();
                eeeList = new ArrayList<>();
                if(!snapshot.exists()){
                    eeeNoData.setVisibility(View.VISIBLE);
                    eee.setVisibility(View.GONE);
                }else{
                    eeeNoData.setVisibility(View.GONE);
                    eee.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        eeeList.add(0,data);
//                        list.addAll(eeeList);
                        progressBar.setVisibility(View.GONE);

                    }
                    eee.setHasFixedSize(true);
                    eee.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(eeeList, getContext());
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
//                list = new ArrayList<>();
                aslList = new ArrayList<>();
                if(!snapshot.exists()){
                    aslNoData.setVisibility(View.VISIBLE);
                    asl.setVisibility(View.GONE);
                }else{
                    aslNoData.setVisibility(View.GONE);
                    asl.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        aslList.add(0,data);
//                        list.addAll(aslList);

                    }
                    asl.setHasFixedSize(true);
                    asl.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(aslList, getContext());
                    asl.setAdapter(adapter);
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
        for (FacultyData item : list){
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
        ((MainActivity) getActivity()).setActionBarTitle("Faculty");
    }


}