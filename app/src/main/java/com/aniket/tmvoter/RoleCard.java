package com.aniket.tmvoter;

import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoleCard {
    String roleName,personName;
    int votes;

    public RoleCard(String roleName, String personName) {
        this.roleName = roleName;
        this.personName = personName;
        this.votes=0;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

}
