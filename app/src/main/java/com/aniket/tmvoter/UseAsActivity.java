package com.aniket.tmvoter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class UseAsActivity extends AppCompatActivity {
    ImageView voterbox,adminbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_as_activity);
        Log.i("timing", "use as activity onCreate: entered");

//        getWindow().setNavigationBarColor(Color.parseColor("#FF5FA0B6"));

        getWindow().setStatusBarColor(Color.parseColor("#FF5FA0B6"));


        voterbox = findViewById(R.id.voterbox);
        voterbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUtils.isAdmin=false;
                Intent toVote = new Intent(getApplicationContext(), MeetingCodeActivity.class);
                finish();
                startActivity(toVote);
            }
        });
        adminbox=findViewById(R.id.exitBox);
        adminbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUtils.isAdmin=true;
                Intent toAdmin = new Intent(getApplicationContext(),PasswordActivity.class);
                startActivity(toAdmin);
            }
        });
    }

    //To make Double Back press to exit

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}