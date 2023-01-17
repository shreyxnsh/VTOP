package com.shreyxnsh.vtop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.shreyxnsh.vtop.ebook.EbookActivity;
import com.shreyxnsh.vtop.ui.aboutus.AboutUsFragment;
import com.shreyxnsh.vtop.ui.faculty.FacultyFragment;
import com.shreyxnsh.vtop.ui.gallery.GalleryFragment;
import com.shreyxnsh.vtop.ui.home.HomeFragment;
import com.shreyxnsh.vtop.ui.notice.NoticeFragment;

import java.util.Objects;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MeowBottomNavigation bottomNavigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Uri uri;
    ConstraintLayout contentView;
    static final float END_SCALE = 0.7f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.contentView);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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
                    case 5:
                        replace(new AboutUsFragment());
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
        bottomNavigationView.add(new MeowBottomNavigation.Model(5, R.drawable.ic_baseline_info_24));

        // after starting app, show first fragment
        replace(new HomeFragment());
        bottomNavigationView.show(1,true);
    }

    private void navigation() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        animateNavigationView();
    }

    private void animateNavigationView() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout.setScrimColor(R.color.dark_blue);
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
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
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
                Toast.makeText(this, "Developer", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "theme", Toast.LENGTH_SHORT).show();
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
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.instagram.android" + getApplicationContext().getPackageName());
                    startActivity(Intent.createChooser(shareIntent, "Share via :"));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to share the application", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.navigation_rate:
                Uri rate_uri = Uri.parse("https://play.google.com/store/apps/details?id=com.deepanshuchaudhary.apps.minivtop" + getApplicationContext().getPackageName());
                Intent rate_intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(rate_intent);
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to rate the application", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}