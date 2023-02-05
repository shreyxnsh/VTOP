package com.shreyxnsh.vtop.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyxnsh.vtop.MainActivity;
import com.shreyxnsh.vtop.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EbookActivity extends AppCompatActivity {

    private RecyclerView ebookRV;
    private DatabaseReference reference;
    private List<EbookData> list;
    private EbookAdapter adapter;

    ShimmerFrameLayout shimmerFrameLayout;
    private LinearLayout shimmerLinearLayout;
    private EditText pdf_search;
    private Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);


        toolbar = findViewById(R.id.appbarEB);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("E-books");
        toolbar.setTitleTextAppearance(this, R.style.poppins_bold);

        ebookRV = findViewById(R.id.ebookRV);
        reference = FirebaseDatabase.getInstance().getReference().child("pdf");
        reference.keepSynced(true);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLinearLayout = findViewById(R.id.shimmerLinearLayout);
        pdf_search = findViewById(R.id.pdf_search);

        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    EbookData data = snapshot.getValue(EbookData.class);
                    list.add(0,data);
                }
                adapter = new EbookAdapter(EbookActivity.this, list);
                adapter.notifyDataSetChanged();
                ebookRV.setLayoutManager(new LinearLayoutManager(EbookActivity.this));
                ebookRV.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerLinearLayout.setVisibility(View.GONE);
                pdf_search.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EbookActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // searching pdf in edit text view
        pdf_search.addTextChangedListener(new TextWatcher() {
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
        ArrayList<EbookData> filterList = new ArrayList<>();
        for (EbookData item : list){
            if (item.getPdfTitle().toLowerCase().contains(text.toLowerCase())){
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