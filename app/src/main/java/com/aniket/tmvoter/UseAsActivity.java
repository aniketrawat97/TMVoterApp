package com.aniket.tmvoter;

import android.content.Intent;
import android.graphics.Color;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
                Intent toMeetingcodecheck = new Intent(getApplicationContext(), MeetingCodeActivity.class);
                startActivity(toMeetingcodecheck);
            }
        });
        adminbox=findViewById(R.id.adminbox);
        adminbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent togeneratecode = new Intent(getApplicationContext(),PasswordActivity.class);
                startActivity(togeneratecode);
            }
        });
    }


}