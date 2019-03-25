package com.aniket.tmvoter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AddPreparedSpeaker extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_prepared_speaker);
        intent=new Intent(AddPreparedSpeaker.this,GuestActivity.class);

        speakable=new ArrayList<>(FirebaseUtils.getMembersList());
        listView=findViewById(R.id.listps);
        fab=findViewById(R.id.myFABPS);
        fabNext=findViewById(R.id.myFABPSnext);
        roleCardList=new ArrayList<>();

        // speakable.addAll(GuestActivity.guests);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roleCardList.add(new RoleCard("Prepared Speaker "+Integer.toString(roleCardList.size()/2+1),""));
                roleCardList.add(new RoleCard("Evaluator "+Integer.toString(roleCardList.size()/2+1),""));
                customAdapter=new CustomAdapter(AddPreparedSpeaker.this,roleCardList,speakable,intent,1000);
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
}