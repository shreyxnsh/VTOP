package com.shreyxnsh.vtop.ui.developers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shreyxnsh.vtop.R;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Developers extends AppCompatActivity {

    private Uri uri;
    private ImageView gmail, bundgmail, nikkigmail, syedgmail;
    private CircleImageView linkedin, bundlink, nikkilink, syedlink;
    private CircleImageView github, bundgit, nikkigit, syedgit;
    private Intent intent;
    private Toolbar toolbar;

    @SuppressLint({"RestrictedApi", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        toolbar = findViewById(R.id.appbarDEV);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Developers");
        toolbar.setTitleTextAppearance(this, R.style.poppins_bold);

        gmail = findViewById(R.id.gmail);
        linkedin = findViewById(R.id.linkedin);
        github = findViewById(R.id.github);
        bundgmail = findViewById(R.id.bundgmail);
        bundlink = findViewById(R.id.bundlink);
        bundgit = findViewById(R.id.bundgith);
        nikkigmail = findViewById(R.id.nikkigmail);
        nikkilink = findViewById(R.id.nikkilink);
        nikkigit = findViewById(R.id.nikkigith);
        syedgmail = findViewById(R.id.dallagmail);
        syedlink = findViewById(R.id.dallalink);
        syedgit = findViewById(R.id.dallagith);

        //buttons to visit the links
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.gmaillink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.linkedinlink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.githublink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        bundgmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("mailto:pratham.budhwani2021@vitbhopal.ac.in");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        bundlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("https://www.linkedin.com/in/pratham-budhwani-045aa41b6/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        bundgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("https://github.com/PrathamBudhwani");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        nikkigmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("mailto:nikita.raut2021@vitbhopal.ac.in");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        nikkilink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("https://www.linkedin.com/in/nikita-raut-700b51214/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        nikkigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("https://github.com/nikita-raut");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        syedgmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("mailto:syed.abbas2021@vitbhopal.ac.in");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        syedlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.linkedinlink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        syedgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.githublink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });



    }
}