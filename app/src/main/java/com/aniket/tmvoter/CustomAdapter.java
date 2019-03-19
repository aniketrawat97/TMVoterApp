package com.aniket.tmvoter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public class CustomAdapter extends BaseAdapter {
    public Context context;
    public List<RoleCard> RoleList;
    public CustomAdapter(Context context, List<RoleCard> roleList) {
        this.context = context;
        RoleList = roleList;

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
        View v=View.inflate(context,R.layout.rolecard,null);
        TextView role=v.findViewById(R.id.role);
        TextView person=v.findViewById(R.id.person);
        Spinner plus=v.findViewById(R.id.plus);
        //setting
        role.setText(RoleList.get(position).getRoleName());
        person.setText(RoleList.get(position).getPersonName());
        ArrayAdapter ad=new ArrayAdapter(context,android.R.layout.simple_spinner_item,PasswordActivity.getMembersList());
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plus.setAdapter(ad);
        return v;
    }

}
