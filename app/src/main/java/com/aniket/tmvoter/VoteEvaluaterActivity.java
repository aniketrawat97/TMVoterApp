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

public class VoteEvaluaterActivity extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    List<RoleCard> roleCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_evaluator);
        int evalNo=0;
        listView=findViewById(R.id.list_vote_eval);

        roleCardList=new ArrayList<>();


        Intent i=new Intent(getApplicationContext(),VoteTableTopicSpeaker.class);

        customAdapter=new CustomAdapter(VoteEvaluaterActivity.this,roleCardList,FirebaseUtils.getMembersList(),i);

        for(int j=0;j<FirebaseUtils.votesList.size();j++){
            Log.i("info", FirebaseUtils.roleList.get(j));
            if(FirebaseUtils.rolePlayerType(FirebaseUtils.roleList.get(j)).equals("EV")){
                evalNo++;
                Log.i("info", "Evaluator found at "+j);
                roleCardList.add(new RoleCard("Evaluator "+evalNo,FirebaseUtils.getRolePlayer("Evaluator "+evalNo),true));
            }
        }

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView roleTemp=view.findViewById(R.id.role);
                Log.i("votes", roleTemp.getText().toString());
                String rolename=roleTemp.getText().toString();
                FirebaseUtils.incrementVotes(rolename);
                startActivity(new Intent(getApplicationContext(),VoteTableTopicSpeaker.class));
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
