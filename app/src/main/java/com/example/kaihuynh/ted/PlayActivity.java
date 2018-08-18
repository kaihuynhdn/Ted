package com.example.kaihuynh.ted;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.kaihuynh.ted.adapter.ViewPagerAdapter;
import com.example.kaihuynh.ted.fragment.PlayingFragment;

public class PlayActivity extends AppCompatActivity implements PlayingFragment.OnDataPass {

    private boolean isPlay = false;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getWidgets();
        init();
        setWidgets();
        setWidgetsListener();
    }

    private void getWidgets() {
        viewPager = findViewById(R.id.view_pager_play);
        tabLayout = findViewById(R.id.tab_layout_play);
        toolbar = findViewById(R.id.toolbar_play_talk);
    }

    private void init() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlayingFragment());
        adapter.addFragment(new Fragment());
        adapter.addFragment(new Fragment());

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Now playing");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setUpTabIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_playing_state);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_up_next_state);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_history_state);
    }

    private void setWidgets() {
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setUpTabIcon();
    }

    private void setWidgetsListener() {

    }

    @Override
    public void onDataPass(boolean isPlay) {
        isPlay = true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent result = new Intent();
                result.putExtra("isPlay", isPlay);
                setResult(RESULT_OK, result);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent result = new Intent();
        result.putExtra("isPlay", true);
        setResult(RESULT_OK, result);
    }
}
