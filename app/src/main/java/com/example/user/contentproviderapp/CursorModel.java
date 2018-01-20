package com.example.user.contentproviderapp;

import android.widget.CheckBox;

/**
 * Created by USER on 1/17/2018.
 */

public class CursorModel {

    private String ContactName;
    private String ContactNumber;
    private Boolean isSelected;


    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
