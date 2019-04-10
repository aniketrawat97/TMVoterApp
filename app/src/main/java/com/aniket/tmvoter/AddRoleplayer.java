package com.aniket.tmvoter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddRoleplayer extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    List<RoleCard> roleCardList;
    static List<String >membersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_role_dynamic);
        listView=findViewById(R.id.asdf);
        membersList=new ArrayList<>();

        roleCardList=new ArrayList<>();
        roleCardList.add(new RoleCard("Toastmaster Of The Day",""));
        roleCardList.add(new RoleCard("TableTopic Master",""));
        roleCardList.add(new RoleCard("General Evaluator",""));
        roleCardList.add(new RoleCard("Grammarian",""));

        membersList = new ArrayList();
        Intent i=new Intent(getApplicationContext(),AddAuxilaryRoleplayerActivity.class);
        customAdapter=new CustomAdapter(AddRoleplayer.this,roleCardList,FirebaseUtils.getMembersList(),i);
        listView.setAdapter(customAdapter);
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
