package com.aniket.tmvoter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GuestActivity extends AppCompatActivity {
EditText guestName;
TextView addBox;
ListView guestList;
static ArrayList<String> guests;
FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        getWindow().setStatusBarColor(Color.parseColor("#FF5FA0B6"));
        guestList=findViewById(R.id.listviewGuestList);
        guestList.setVisibility(View.INVISIBLE);
        addBox=findViewById(R.id.TextviewAdd);
        guestName=findViewById(R.id.guestName);
        guests=new ArrayList<>();
        fab=findViewById(R.id.myFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(getApplicationContext(),AddTableTopicSpeakerActivity.class);
            startActivity(intent);
            }
        });
        addBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=guestName.getText().toString();
                if(!name.equals("")){
                    guestList.setVisibility(View.VISIBLE);
                    guests.add(name);
                    ArrayAdapter ad=new ArrayAdapter(GuestActivity.this,R.layout.simple_list_item_1,guests);
                    guestList.setAdapter(ad);
                    guestName.setText("");
                }
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
