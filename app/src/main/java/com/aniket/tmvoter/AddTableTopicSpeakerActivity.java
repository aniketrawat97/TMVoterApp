package com.aniket.tmvoter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddTableTopicSpeakerActivity extends AppCompatActivity {
ArrayList<String> speakable;
    CustomAdapter customAdapter;
    ListView listView;
    List<RoleCard> roleCardList;
    static List<String >membersList;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_topic_speaker);

        speakable=new ArrayList<>(FirebaseUtils.getMembersList());
        listView=findViewById(R.id.listtts);
        fab=findViewById(R.id.myFABTT);
        roleCardList=new ArrayList<>();

        speakable.addAll(GuestActivity.guests);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roleCardList.add(new RoleCard("TableTopic Speaker "+Integer.toString(roleCardList.size()+1),""));
                Intent i=new Intent(AddTableTopicSpeakerActivity.this,GeneratingCodeActivity.class);
                customAdapter=new CustomAdapter(AddTableTopicSpeakerActivity.this,roleCardList,speakable,i);
                listView.setAdapter(customAdapter);
            }
        });

    }
}




