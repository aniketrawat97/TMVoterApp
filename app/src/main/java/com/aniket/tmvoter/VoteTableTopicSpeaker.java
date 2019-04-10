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

public class VoteTableTopicSpeaker extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    List<RoleCard> roleCardList;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_table_topic_speaker);
        int ttNo=0;
        listView=findViewById(R.id.list_vote_tts);

        roleCardList=new ArrayList<>();


        i=new Intent(getApplicationContext(),MeetingCodeActivity.class);

        customAdapter=new CustomAdapter(VoteTableTopicSpeaker.this,roleCardList,FirebaseUtils.getMembersList(),i);

        try {
            for (int j = 0; j < FirebaseUtils.votesList.size(); j++) {
                Log.i("info", FirebaseUtils.roleList.get(j));
                if (FirebaseUtils.rolePlayerType(FirebaseUtils.roleList.get(j)).equals("TT")) {
                    ttNo++;
                    Log.i("info", "TableTopic Speaker found at " + j);
                    roleCardList.add(new RoleCard("TableTopic Speaker " + ttNo, FirebaseUtils.getRolePlayer("TableTopic Speaker " + ttNo), true));
                }
            }
        }catch (Exception e){
            Log.i("info", "Exception - "+e);
        }

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView roleTemp=view.findViewById(R.id.role);
                Log.i("votes", roleTemp.getText().toString());
                String rolename=roleTemp.getText().toString();
                FirebaseUtils.incrementVotes(rolename);
                if(FirebaseUtils.isAdmin){
                    i=new Intent(getApplicationContext(),ScoreBoardActivity.class);
                }else{
                    i=new Intent(getApplicationContext(),ThankyouActivity.class);
                }
                startActivity(i);
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
