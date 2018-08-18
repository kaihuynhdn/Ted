package com.example.kaihuynh.ted;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class TalkActivity extends AppCompatActivity {

    private boolean isPlay = false;
    private static final int SEND_CODE = 123;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        getWidgets();
        init();
        setWidgets();
        setWidgetsListener();
    }

    private void getWidgets() {
        toolbar = findViewById(R.id.toolbar_talk);
        fab = findViewById(R.id.fab_play_talk);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setWidgets() {

    }

    private void setWidgetsListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TalkActivity.this, PlayActivity.class);
                startActivityForResult(intent, SEND_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SEND_CODE && resultCode == RESULT_OK){
            isPlay = data.getBooleanExtra("isPlay", false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent result = new Intent();
                result.putExtra("isPlay", true);
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
