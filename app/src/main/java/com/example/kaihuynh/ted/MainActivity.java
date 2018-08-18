package com.example.kaihuynh.ted;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kaihuynh.ted.adapter.ViewPagerAdapter;
import com.example.kaihuynh.ted.custom.MiniControlSwipeListener;
import com.example.kaihuynh.ted.fragment.MainFragment;
import com.example.kaihuynh.ted.fragment.NewestFragment;
import com.example.kaihuynh.ted.fragment.PlayListFragment;
import com.ohoussein.playpause.PlayPauseView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private FloatingActionButton fab;
    private PlayPauseView playPauseView;

    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWidgets();
        init();
        setWidgets();
        setWidgetsListener();


    }

    private void getWidgets() {
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.app_bar_layout);
        fab = findViewById(R.id.fab_search);
        playPauseView = findViewById(R.id.btn_play_pause);
        linearLayout = findViewById(R.id.ll_mini_control);
    }

    private void init() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment());
        adapter.addFragment(new PlayListFragment());
        adapter.addFragment(new Fragment());
        adapter.addFragment(new Fragment());
        adapter.addFragment(new Fragment());

        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setTitle("  Talks");

        linearLayout.setVisibility(View.GONE);
    }

    private void setUpTabIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.list_icon_state);
        tabLayout.getTabAt(1).setIcon(R.drawable.playlist_icon_state);
        tabLayout.getTabAt(2).setIcon(R.drawable.podcasts_icon_state);
        tabLayout.getTabAt(3).setIcon(R.drawable.lamp_icon_state);
        tabLayout.getTabAt(4).setIcon(R.drawable.user_icon_state);
    }

    private void setWidgets() {
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
        setUpTabIcon();

        ViewTreeObserver observer = linearLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LinearLayout.LayoutParams layoutParams
                                = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        if (linearLayout.getVisibility() == View.VISIBLE) {
                            layoutParams.setMargins(0, 0, 0, linearLayout.getHeight()-40);
                        } else {
                            layoutParams.setMargins(0, 0, 0, 0);
                        }
                        viewPager.setLayoutParams(layoutParams);
                    }
                });

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setWidgetsListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                appBarLayout.setExpanded(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        playPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPauseView.toggle();
            }
        });

        linearLayout.setOnTouchListener(new MiniControlSwipeListener(this){

            @Override
            public void onSwipeRight() {
                linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onSwipeLeft() {
                linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onSwipeTop() {

            }

            @Override
            public void onSwipeBottom() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewestFragment.SEND_CODE && resultCode == RESULT_OK){
            boolean isPlay = data.getBooleanExtra("isPlay", false);
            if (isPlay){
                linearLayout.setVisibility(View.VISIBLE);
            }
            Toast.makeText(MainActivity.this, String.valueOf(isPlay), Toast.LENGTH_SHORT).show();
        }
    }
}
