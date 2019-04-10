package com.aniket.tmvoter;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddTableTopicSpeakerActivity extends AppCompatActivity {
ArrayList<String> speakable;
    CustomAdapter customAdapter;
    ListView listView;
    List<RoleCard> roleCardList;
    static List<String >membersList;
    FloatingActionButton fab,fabNext;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_topic_speaker);
        intent=new Intent(AddTableTopicSpeakerActivity.this,GeneratingCodeActivity.class);

        speakable=new ArrayList<>(FirebaseUtils.getMembersList());
        listView=findViewById(R.id.listtts);
        fab=findViewById(R.id.myFABTT);
        fabNext=findViewById(R.id.myFABTTnext);
        roleCardList=new ArrayList<>();

        speakable.addAll(GuestActivity.guests);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roleCardList.add(new RoleCard("TableTopic Speaker "+Integer.toString(roleCardList.size()+1),""));
                customAdapter=new CustomAdapter(AddTableTopicSpeakerActivity.this,roleCardList,speakable,intent,1000);
                listView.setAdapter(customAdapter);
            }
        });
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
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




