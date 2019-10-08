package com.example.Moodle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Event_Adapter extends RecyclerView.Adapter<Event_Adapter.ChatViewHolder>{
    private List<Event_Details> event_list;
    private LayoutInflater layoutInflater;
    private Context context;
    private final String Tag="Event_Adapter";
    //private OnNoteListner onNoteListner;

    public Event_Adapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        event_list = new ArrayList<>();
        //this.onNoteListner = onNoteListner;
    }
    /*public Event_Adapter(Context context,ArrayList<Organiser_Profile> list){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        list = new ArrayList<>();
        //this.onNoteListner = onNoteListner;
    }*/
    public void setData(List<Event_Details> event_list){
        this.event_list.clear();
        this.event_list.addAll(event_list);
        notifyDataSetChanged();
    }
    public void addData(List<Event_Details> event_list){
        this.event_list.addAll(event_list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_event_layout,parent,false);
        Log.e(TAG,"OnCreateViewHolder");
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Event_Adapter.ChatViewHolder holder, int position) {
        Log.e(TAG,"OnBindViewHolder");

        /*holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(See_All_Events.this,Verify_Orgniser.class);

            }
        });*/
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return event_list.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
        private TextView txt_com_name,txt_event_name,txt_event_date,txt_ctc_no;
        private ConstraintLayout constraintLayout;
        //OnNoteListner onNoteListner;

        public ChatViewHolder(@NonNull View view){
            super(view);
            txt_com_name = view.findViewById(R.id.tv_com_name);
            txt_event_name = view.findViewById(R.id.event_name);
            txt_event_date = view.findViewById(R.id.event_date);
            txt_ctc_no = view.findViewById(R.id.ctc_person);
            constraintLayout = view.findViewById(R.id.constarintLayout);
            //this.onNoteListner = onNoteListner;
        }
        public void bind(){
            int position = getAdapterPosition();
            Event_Details event_position = event_list.get(position);
            txt_com_name.setText(event_position.getCom_name());
            txt_event_name.setText(event_position.getEvent_name());
            txt_event_date.setText(event_position.getEvent_date());
            txt_ctc_no.setText(event_position.getCtc_no());
            /*if(event_position.getCommit_img()==null){
                //Toast.makeText(context, "Could not upload image currently...", Toast.LENGTH_SHORT).show();
            }*/
        }
        /*@Override
        public void onClick(View view) {
            onNoteListner.onNoteClick(getAdapterPosition());                            //This gets the exact position of the point where there was a click...
        }*/
    }

    /*public interface OnNoteListner{
        void onNoteClick(int position);
    }*/
}