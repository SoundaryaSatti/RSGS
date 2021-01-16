package com.integro.rsgs.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.integro.rsgs.R;
import com.integro.rsgs.adapters.ViewPageAdapter;
import com.integro.rsgs.model.LeaderShip;
import com.integro.rsgs.model.Ministries;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import static com.integro.rsgs.firebase.MyFirebaseMessagingService.NEWS_KEY;
import static com.integro.rsgs.firebase.MyFirebaseMessagingService.NOTIFICATION_KEY;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyFirebaseMsgService";


    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPageAdapter adapter;


    TextView tvWWR, tvLeaderShip, tvMinistries, tvMedia;
    ImageView ivCall, ivMail, ivFacebook;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        ivCall = findViewById(R.id.iv_call);
        ivMail = findViewById(R.id.iv_mail);
        ivFacebook = findViewById(R.id.iv_facebook);
        tvWWR = findViewById(R.id.tv_WWR);
        tvLeaderShip = findViewById(R.id.tv_Leadership);
        tvMinistries = findViewById(R.id.tv_Ministries);
        tvMedia = findViewById(R.id.tv_Media);


        adapter = new ViewPageAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);//setText("home")//;
        tabLayout.getTabAt(1).setIcon(R.drawable.newss);
        tabLayout.getTabAt(2).setIcon(R.drawable.notifications);
        tabLayout.getTabAt(3).setIcon(R.drawable.www1);

         FirebaseMessaging.getInstance().subscribeToTopic("gs");

        boolean isFCMIntent = getIntent().getBooleanExtra(TAG, false);
        if (isFCMIntent) {
            String type = getIntent().getExtras().getString("type");
            switch (type) {
                case NEWS_KEY:
                    viewPager.setCurrentItem(1);
                    break;
                case NOTIFICATION_KEY:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
        final int colorYellow = ContextCompat.getColor(getApplicationContext(), R.color.colorYellow);
        final int colorWhiite = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(colorYellow, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(colorWhiite, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                final CharSequence[] phone = new CharSequence[]{"8156956995"};
                String phone1 = "8156956995";
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone1, null));
                startActivity(intentCall);
            }
        });
        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailintent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject" + " " + "&body" + " " + "&to=" + "goodshepherdbangalore@gmail.com");
                mailintent.setData(data);
                startActivity(mailintent);
            }
        });
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFacebbok = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("https://www.facebook.com/Good-Shepherd-Bangalore-425035744745798/");
                intentFacebbok.setData(data);
                startActivity(intentFacebbok);

            }
        });

        tvWWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWWR = new Intent(MainActivity.this, WhereWeAreActivity.class);
                startActivity(intentWWR);
            }
        });
        tvLeaderShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLeaderShip = new Intent(MainActivity.this, LeaderShipActivity.class);
                startActivity(intentLeaderShip);
            }
        });
        tvMinistries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMinistries = new Intent(MainActivity.this, MinistriesActivity.class);
                startActivity(intentMinistries);
            }
        });
        tvMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMedia = new Intent(MainActivity.this, MediaActivity.class);
                startActivity(intentMedia);
            }
        });
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit");
        AlertDialog.Builder builder = alertDialogBuilder.setMessage("Do you really want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //System.exit(0);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
