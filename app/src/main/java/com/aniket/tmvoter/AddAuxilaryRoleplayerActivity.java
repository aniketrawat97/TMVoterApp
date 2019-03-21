package com.aniket.tmvoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AddAuxilaryRoleplayerActivity extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    List<RoleCard> roleCardList;
    static List<String >membersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_auxilary_roleplayer);
        listView=findViewById(R.id.listaux);
        membersList=new ArrayList<>();

        roleCardList=new ArrayList<>();
        roleCardList.add(new RoleCard("Timer",""));
        roleCardList.add(new RoleCard("Ah Counter",""));
        roleCardList.add(new RoleCard("Hark Master",""));

        membersList = new ArrayList();
        Intent i=new Intent(getApplicationContext(),GuestActivity.class);
        customAdapter=new CustomAdapter(AddAuxilaryRoleplayerActivity.this,roleCardList,FirebaseUtils.getMembersList(),i);
        listView.setAdapter(customAdapter);
    }
}