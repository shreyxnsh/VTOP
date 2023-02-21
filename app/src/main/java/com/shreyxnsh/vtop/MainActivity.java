package com.shreyxnsh.vtop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.shreyxnsh.vtop.ebook.EbookActivity;
import com.shreyxnsh.vtop.ui.clubs.ClubActivity;
import com.shreyxnsh.vtop.ui.developers.Developers;
import com.shreyxnsh.vtop.ui.faculty.FacultyFragment;
import com.shreyxnsh.vtop.ui.feedbacks.FeedbackActivity;
import com.shreyxnsh.vtop.ui.gallery.GalleryFragment;
import com.shreyxnsh.vtop.ui.gpacalc.GpaCalculator;
import com.shreyxnsh.vtop.ui.home.HomeFragment;
import com.shreyxnsh.vtop.ui.notice.NoticeData;
import com.shreyxnsh.vtop.ui.notice.NoticeFragment;

import java.util.Objects;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public MeowBottomNavigation bottomNavigationView;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;
    public NavigationView navigationView;
    public Uri uri;
    ConstraintLayout contentView;
    static final float END_SCALE = 0.7f;
    public Toolbar toolbar;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public int checkedItem;
    public String selected;
    public final String CHECKEDITEM  = "checked_item";


    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("notification");
        FirebaseMessaging.getInstance().subscribeToTopic("notice");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.contentView);
        toolbar = findViewById(R.id.appbar);

        NoticeData noticeData = new NoticeData();

        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        switch (getCheckedItem()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle("Dashboard");
            toolbar.setTitleTextAppearance(this, R.style.poppins_bold);


        navigation();
        bottomNavigation();



        //bottom nav code for onClickListener
        bottomNavigationView.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        replace(new HomeFragment());
                        break;
                    case 2:
                        replace(new NoticeFragment());
                        break;
                    case 3:
                        replace(new FacultyFragment());
                        break;
                    case 4:
                        replace(new GalleryFragment());
                        break;

                }
                return null;
            }
        });

        //if user repeats to click on a certain fragment icon
        bottomNavigationView.setOnReselectListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                Toast.makeText(MainActivity.this, "Uh Oh! You're already here.", Toast.LENGTH_SHORT).show();
                return null;
            }
        });

    }

    private void bottomNavigation() {
        //bottom nav code for setting id and icons
        bottomNavigationView.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        bottomNavigationView.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_notifications_active_24));
        bottomNavigationView.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_people_24));
        bottomNavigationView.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_photo_library_24));

        // after starting app, show first fragment
        replace(new HomeFragment());
        bottomNavigationView.show(1, true);
    }

    private void navigation() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        animateNavigationView();
    }

    @SuppressLint("ResourceAsColor")
    private void animateNavigationView() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.navigation_developer:
                startActivity(new Intent(this, Developers.class));
                break;
            case R.id.navigation_video:
                uri = Uri.parse(getString(R.string.vityarthi_link));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.navigation_ebookpdf:
                startActivity(new Intent(this, EbookActivity.class));
                break;
            case R.id.navigation_theme:
                showdialog();
                break;
            case R.id.navigation_cgpa:
                startActivity(new Intent(this, GpaCalculator.class));
                break;
            case R.id.navigation_clubs:
                startActivity(new Intent(this, ClubActivity.class));
                break;
            case R.id.navigation_feeback:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.navigation_websites:
                uri = Uri.parse(getString(R.string.vtop_website_link));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.navigation_shareus:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "VTOP");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Download VTOP - VIT Bhopal and take charge of your college experience : " + "https://play.google.com/store/apps/details?id=com.shreyxnsh.vtop");
                    startActivity(Intent.createChooser(shareIntent, "Share via :"));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to share the application", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.navigation_rate:
                Uri rate_uri = Uri.parse("https://play.google.com/store/apps/details?id=com.shreyxnsh.vtop");
                Intent rate_intent = new Intent(Intent.ACTION_VIEW, rate_uri);
                try {
                    startActivity(rate_intent);
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to rate the application", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private void showdialog() {

        String[] themes = this.getResources().getStringArray(R.array.theme);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.AlertDialog);
        builder.setTitle("Select theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selected = themes[which];
                checkedItem = which;

            }
        });
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (selected == null){
                    selected = themes[which];
                    checkedItem = which;
                }
                switch (selected){
                    case "Default":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "Dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "Light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
                setCheckedItem(checkedItem);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int getCheckedItem(){
        return sharedPreferences.getInt(CHECKEDITEM,0);

    }
    private void setCheckedItem(int i){
        editor.putInt(CHECKEDITEM,i);
        editor.apply();
    }



    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


}