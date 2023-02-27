package com.shreyxnsh.vtop.ui.notice;

import static android.content.Context.CLIPBOARD_SERVICE;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class NoticeFragment extends Fragment {
    private RecyclerView noticeRV;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;
    private DatabaseReference reference;
    private LottieAnimationView progress_anim;
    private TextView noticeTitle;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        noticeRV = view.findViewById(R.id.noticeRV);
        reference = FirebaseDatabase.getInstance().getReference().child("Notice");
        reference.keepSynced(true);

        progress_anim = view.findViewById(R.id.progress_anim);

        noticeRV.setLayoutManager(new LinearLayoutManager(getContext()));
        noticeRV.setHasFixedSize(true);

//        noticeTitle = view.findViewById(R.id.noticeTitle);
//        noticeTitle.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                ClipboardManager clipboardManager = (ClipboardManager) ContextCompat.getSystemService(getActivity(), CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("Faculty Details", noticeTitle.getText().toString());
//                clipboardManager.setPrimaryClip(clip);
//
//                Toast.makeText(getActivity(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });



        getNotice();
        return view;
    }

    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //initialising list
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // getting data from NoticeData class
                    NoticeData data = snapshot.getValue(NoticeData.class);
                    // adding in the list
                    list.add(0,data);
                }
                adapter = new NoticeAdapter(getContext(), list);
                adapter.notifyDataSetChanged();
                progress_anim.setVisibility(View.GONE);
                noticeRV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progress_anim.setVisibility(View.GONE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Notices");
    }

//    // hide action bar
//    @Override
//    public void onResume() {
//        super.onResume();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
//    }

}
