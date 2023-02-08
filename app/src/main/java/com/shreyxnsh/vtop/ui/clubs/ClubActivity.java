package com.shreyxnsh.vtop.ui.clubs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyxnsh.vtop.R;
import com.shreyxnsh.vtop.ebook.EbookActivity;
import com.shreyxnsh.vtop.ebook.EbookAdapter;
import com.shreyxnsh.vtop.ebook.EbookData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClubActivity extends AppCompatActivity {

    private RecyclerView clubsRV;
    private DatabaseReference reference;
    private List<ClubData> list;
    private ClubAdapter adapter;

    ShimmerFrameLayout shimmerFrameLayout;
    private LinearLayout shimmerLinearLayout;
    private EditText club_search;
    private Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        toolbar = findViewById(R.id.appbarClub);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Clubs");
        toolbar.setTitleTextAppearance(this, R.style.poppins_bold);

        clubsRV = findViewById(R.id.clubsRV);
        reference = FirebaseDatabase.getInstance().getReference().child("Clubs");
        reference.keepSynced(true);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLinearLayout = findViewById(R.id.shimmerLinearLayout);
        club_search = findViewById(R.id.club_search);

        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ClubData data = snapshot.getValue(ClubData.class);
                    list.add(0,data);
                }
                adapter = new ClubAdapter(ClubActivity.this, list);
                adapter.notifyDataSetChanged();
                clubsRV.setLayoutManager(new LinearLayoutManager(ClubActivity.this));
                clubsRV.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerLinearLayout.setVisibility(View.GONE);
                club_search.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ClubActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // searching pdf in edit text view
        club_search.addTextChangedListener(new TextWatcher() {
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
        ArrayList<ClubData> filterList = new ArrayList<>();
        for (ClubData item : list){
            if (item.getClubname().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        adapter.FilteredList(filterList);

    }


    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        shimmerFrameLayout.startShimmer();
        super.onRestart();
    }
}