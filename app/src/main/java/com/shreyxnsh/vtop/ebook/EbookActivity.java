package com.shreyxnsh.vtop.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyxnsh.vtop.R;

import java.util.ArrayList;
import java.util.List;

public class EbookActivity extends AppCompatActivity {

    private RecyclerView ebookRV;
    private DatabaseReference reference;
    private List<EbookData> list;
    private EbookAdapter adapter;

    ShimmerFrameLayout shimmerFrameLayout;
    private LinearLayout shimmerLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        ebookRV = findViewById(R.id.ebookRV);
        reference = FirebaseDatabase.getInstance().getReference().child("pdf");
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLinearLayout = findViewById(R.id.shimmerLinearLayout);
        
        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    EbookData data = snapshot.getValue(EbookData.class);
                    list.add(data);
                }
                adapter = new EbookAdapter(EbookActivity.this, list);
                adapter.notifyDataSetChanged();
                ebookRV.setLayoutManager(new LinearLayoutManager(EbookActivity.this));
                ebookRV.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerLinearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EbookActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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