package com.aniket.tmvoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;

public class UseAsActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ImageView voterbox,adminbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_as_activity);
//        getWindow().setNavigationBarColor(Color.parseColor("#FF5FA0B6"));
//        getWindow().setStatusBarColor(Color.parseColor("#FF5FA0B6"));
        voterbox = findViewById(R.id.voterbox);
        voterbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMeetingcodecheck = new Intent(getApplicationContext(), MeetingCodeActivity.class);
                startActivity(toMeetingcodecheck);
            }
        });
        adminbox=findViewById(R.id.adminbox);
        adminbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent togeneratecode = new Intent(getApplicationContext(), GeneratingCodeActivity.class);
                startActivity(togeneratecode);
            }
        });
    }

}