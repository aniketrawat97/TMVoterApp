package com.aniket.tmvoter;

import android.widget.Spinner;

public class RoleCard {
    String roleName,personName;

    public RoleCard(String roleName, String personName) {
        this.roleName = roleName;
        this.personName = personName;
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
    public void setName(String name){

    }
}
