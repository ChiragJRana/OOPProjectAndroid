package com.example.Moodle;

public class Event_Details {

    private String event_name,event_date,ctc_no;

    public Event_Details(String event_name, String event_date, String ctc_no) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.ctc_no = ctc_no;
    }

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
}
