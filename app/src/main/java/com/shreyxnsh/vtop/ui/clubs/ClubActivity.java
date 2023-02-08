package com.shreyxnsh.vtop.ui.clubs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DatabaseReference;
import com.shreyxnsh.vtop.R;
import com.shreyxnsh.vtop.ebook.EbookAdapter;
import com.shreyxnsh.vtop.ebook.EbookData;

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
    }
}