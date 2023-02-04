package com.shreyxnsh.vtop.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyxnsh.vtop.MainActivity;
import com.shreyxnsh.vtop.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

  RecyclerView convoRV, advityaRV, vicharRV, aadhavRV;
  GalleryAdapter adapter;
  DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);

        convoRV = view.findViewById(R.id.convoRV);
        advityaRV = view.findViewById(R.id.advityaRV);
        vicharRV = view.findViewById(R.id.vicharRV);
        aadhavRV = view.findViewById(R.id.aadhavRV);

        reference = FirebaseDatabase.getInstance().getReference().child("gallery");
        reference.keepSynced(true);

        getAadhavImage();
        getConvoImage();
        getAdvityaImage();
        getVivaanImage();

        return view;
    }
    private void getAadhavImage() {
        reference.child("Aadhav ").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(),imageList);
                //setting layout manager in recyclerView
                aadhavRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
                aadhavRV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getVivaanImage() {
        reference.child("Vivaan").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(),imageList);
                //setting layout manager in recyclerView
                vicharRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
                vicharRV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getAdvityaImage() {
        reference.child("Advitya'22").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(),imageList);
                //setting layout manager in recyclerView
                advityaRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
                advityaRV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getConvoImage() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(),imageList);
                //setting layout manager in recyclerView
                convoRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
                convoRV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Event Gallery");
    }
}