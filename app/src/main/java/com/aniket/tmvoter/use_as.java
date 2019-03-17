package com.aniket.tmvoter;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;

public class use_as extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ImageView voterbox,adminbox;
    public void voterClicked() {
        Intent toMeetingcodecheck = new Intent(getApplicationContext(), MeetingCode.class);
        startActivity(toMeetingcodecheck);
    }
    public void adminClicked() {
        Intent toMeetingcodecheck = new Intent(getApplicationContext(),MeetingCode.class);
        startActivity(toMeetingcodecheck);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_as_activity);
        getWindow().setNavigationBarColor(Color.parseColor("#FF5FA0B6"));
        getWindow().setStatusBarColor(Color.parseColor("#FF5FA0B6"));
        voterbox = findViewById(R.id.voterbox);
        voterbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMeetingcodecheck = new Intent(getApplicationContext(), MeetingCode.class);
                startActivity(toMeetingcodecheck);
            }
        });
        adminbox=findViewById(R.id.adminbox);
        adminbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent togeneratecode = new Intent(getApplicationContext(),generatingCodeActivity.class);
                startActivity(togeneratecode);
            }
        });
    }

}