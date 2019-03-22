package com.aniket.tmvoter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    public Context context;
    public List<RoleCard> roleList;
    public List<String> membersList;
    int itemCount;
    public Intent intent;
    public CustomAdapter(Context context, List<RoleCard> roleList,List<String>membersList,Intent intent,int itemCount) {
        this.context = context;
        this.roleList = roleList;
        this.itemCount=itemCount;
        this.intent=intent;
        this.membersList=membersList;
    }
    public CustomAdapter(Context context, List<RoleCard> roleList,List<String>membersList,Intent intent) {
        this.context = context;
        this.roleList = roleList;
        this.itemCount=roleList.size();
        this.intent=intent;
        this.membersList=membersList;
    }

    @Override
    public int getCount() {
        return roleList.size();
    }

    @Override
    public Object getItem(int position) {
        return roleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v=View.inflate(context,R.layout.rolecard,null);

        final TextView role=v.findViewById(R.id.role);
        final TextView person=v.findViewById(R.id.person);
        final Spinner plus = v.findViewById(R.id.plus);

        //setting up text to textviews

        final int posRoleList=position;

        role.setText(roleList.get(posRoleList).getRoleName());

        person.setText(roleList.get(posRoleList).getPersonName());

        if(roleList.get(posRoleList).getFilled()){
            plus.setVisibility(View.INVISIBLE);
        }

        //setting up Spinner and its Adapter

        ArrayAdapter ad=new ArrayAdapter(context,android.R.layout.simple_spinner_item,membersList); //PasswordActivity.getMembersList()
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plus.setAdapter(ad);

        //preventing Spinner to false trigger

        int initialSelectedPosition=plus.getSelectedItemPosition();
        plus.setSelection(initialSelectedPosition, false);

        //setting up Spinner's Listener

        plus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RoleCard temp=roleList.get(posRoleList);



                Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
                String nameSelected="TM "+membersList.get(position);
                person.setText(nameSelected);
                plus.setVisibility(View.INVISIBLE);
                temp.setPersonName(nameSelected); // saving the name back in the rolelist
                temp.setFilled(true);

                FirebaseUtils.updateCandidateName(person.getText().toString(),role.getText().toString(),0);

                itemCount--;

                if(itemCount<1){  // To jump on next activity on completion
                    context.startActivity(intent);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        return v;
    }
}
