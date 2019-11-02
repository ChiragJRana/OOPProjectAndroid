package com.example.Moodle;

import android.net.Uri;

public class Event_Details {

    private Uri uri;
    private String com_name;
    private String event_name;
    private String event_date;
    private String ctc_no;
    private String pp_url;
    private String event_time;

    public Event_Details(Uri uri, String com_name, String event_name, String event_date,String Time, String ctc_no) {
        this.uri = uri;
        this.com_name = com_name;
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_time = Time;
        this.ctc_no = ctc_no;
    }
    public Event_Details(String com_name, String event_name, String event_date,String Time, String ctc_no) {
        this.com_name = com_name;
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_time = Time;
        this.ctc_no = ctc_no;
    }
    public Event_Details(String event_name, String event_date,String Time,String ctc_no) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_time = Time;
        this.ctc_no = ctc_no;
    }

    public Event_Details() {}

    public String getPp_url() {
        return pp_url;
    }
    public void setPp_url(String pp_url) {
        this.pp_url = pp_url;
    }

    public String getCom_name() { return com_name;}
    public void setCom_name(String com_name) { this.com_name = com_name; }

    public void setEvent_time(String time){ this.event_time = time;}
    public String getEvent_time(){return event_time;}


    public String getEvent_name() {
        return event_name;
    }
    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_date() {
        return event_date;
    }
    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getCtc_no() {
        return ctc_no;
    }
    public void setCtc_no(String ctc_no) {
        this.ctc_no = ctc_no;
    }

    public Uri getUri() { return uri;   }
    public void setUri(Uri uri) { this.uri = uri;  }
}
