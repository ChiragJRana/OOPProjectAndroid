package com.example.Moodle;

import android.widget.ImageView;
import android.widget.TextView;

public class Commitee_Profile {
    private String commit_name;
    private String commit_img;

    public Commitee_Profile(){

    }

    public Commitee_Profile(String commit_name) {
        this.commit_name = commit_name;
    }

    public String getCommit_name() {
        return commit_name;
    }

    public void setCommit_name(String commit_name) {
        this.commit_name = commit_name;
    }

    public String getCommit_img() {
        return commit_img;
    }

    public void setCommit_img(String commit_img) {
        this.commit_img = commit_img;
    }
}
