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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    public Context context;
    public List<RoleCard> RoleList;
    private int itemCount;
    public CustomAdapter(Context context, List<RoleCard> roleList) {
        this.context = context;
        RoleList = roleList;
        this.itemCount=roleList.size();
    }

    @Override
    public int getCount() {
        return RoleList.size();
    }

    @Override
    public Object getItem(int position) {
        return RoleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v=View.inflate(context,R.layout.rolecard,null);

        TextView role=v.findViewById(R.id.role);
        final TextView person=v.findViewById(R.id.person);
        final Spinner plus=v.findViewById(R.id.plus);
        //setting
        final List<String> samplelist=new ArrayList();
        samplelist.add("Sample1");
        samplelist.add("Sample2");
        samplelist.add("Sample3");
        samplelist.add("Sample4");

        role.setText(RoleList.get(position).getRoleName());
        person.setText(RoleList.get(position).getPersonName());
        ArrayAdapter ad=new ArrayAdapter(context,android.R.layout.simple_spinner_item,PasswordActivity.getMembersList());//PasswordActivity.getMembersList()
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plus.setAdapter(ad);
        int initialSelectedPosition=plus.getSelectedItemPosition();
        plus.setSelection(initialSelectedPosition, false);

        plus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();

                person.setText(PasswordActivity.getMembersList().get(position).toString());
                plus.setVisibility(View.INVISIBLE);
                itemCount--;
                if(itemCount<1){
                    Intent intent=new Intent(context,GuestActivity.class);
                    context.startActivity(intent);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        return v;
    }
}
