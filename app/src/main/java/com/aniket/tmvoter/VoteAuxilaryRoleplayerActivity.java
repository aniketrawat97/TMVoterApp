package com.aniket.tmvoter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VoteAuxilaryRoleplayerActivity extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    List<RoleCard> roleCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_auxilaryroleplayer);
        listView=findViewById(R.id.list_vote_arp);

        roleCardList=new ArrayList<>();
        roleCardList.add(new RoleCard("Timer",FirebaseUtils.getRolePlayer("Timer"),true));
        roleCardList.add(new RoleCard("Ah Counter",FirebaseUtils.getRolePlayer("Ah Counter"),true));
        roleCardList.add(new RoleCard("Hark Master",FirebaseUtils.getRolePlayer("Hark Master"),true));

        Intent i=new Intent(getApplicationContext(),VotePrepSpeakerActivity.class);

        customAdapter=new CustomAdapter(VoteAuxilaryRoleplayerActivity.this,roleCardList,FirebaseUtils.getMembersList(),i);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView roleTemp=view.findViewById(R.id.role);
                Log.i("votes", roleTemp.getText().toString());
                String rolename=roleTemp.getText().toString();
                FirebaseUtils.incrementVotes(rolename);
                startActivity(new Intent(getApplicationContext(),VotePrepSpeakerActivity.class));
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
