package com.example.Moodle;

import android.widget.EditText;

public class Organiser_Profile {

    private String com_name,com_email;

    public Organiser_Profile(){

    }

    public Organiser_Profile(String com_name, String com_email) {
        this.com_name = com_name;
        this.com_email = com_email;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getCom_email() {
        return com_email;
    }

    public void setCom_email(String com_email) {
        this.com_email = com_email;
    }
}
