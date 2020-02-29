package com.example.leadmanagementsample.ui.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.leadmanagementsample.R;
import com.example.leadmanagementsample.ui.deal_list.DealListActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToNoteListActivity();
            }
        }, 3000);
    }

    private void navigateToNoteListActivity() {
        Intent intent = new Intent(SplashActivity.this, DealListActivity.class);
        startActivity(intent);
        finish();
    }
}
