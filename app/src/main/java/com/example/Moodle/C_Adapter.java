package com.example.Moodle;

import android.content.Context;
import android.content.Intent;
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

import com.google.android.gms.common.data.DataHolder;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class C_Adapter extends RecyclerView.Adapter<C_Adapter.ChatViewHolder>{
    private List<Commitee_Profile> commitiesnames;
    private LayoutInflater layoutInflater;
    private Context context;
    private final String Tag="C_Adapter";
    //private OnNoteListner onNoteListner;

    public C_Adapter(Context context,List<Commitee_Profile> list){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        commitiesnames = new ArrayList<>();
        //this.onNoteListner = onNoteListner;
    }
    /*public C_Adapter(Context context,ArrayList<Organiser_Profile> list){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        list = new ArrayList<>();
        //this.onNoteListner = onNoteListner;
    }*/
    public void setData(List<Commitee_Profile> commitiesnames){
        this.commitiesnames.clear();
        this.commitiesnames.addAll(commitiesnames);
        notifyDataSetChanged();
    }
    public void addData(List<Commitee_Profile> commitiesnames){
        this.commitiesnames.addAll(commitiesnames);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_organiser_layout,parent,false);
        Log.e(TAG,"OnCreateViewHolder");
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull C_Adapter.ChatViewHolder holder, int position) {
        Log.e(TAG,"OnBindViewHolder");

        /*holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(See_Organiser.this,Verify_Orgniser.class);

            }
        });*/
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return commitiesnames.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView text_com_name;
        private Button btn_org_detail;
        private ConstraintLayout constraintLayout;
        OnNoteListner onNoteListner;

        public ChatViewHolder(@NonNull View view){
            super(view);
            btn_org_detail = view.findViewById(R.id.btn_org_detail);
            imageView = view.findViewById(R.id.com_logo);
            text_com_name = view.findViewById(R.id.com_name);
            constraintLayout = view.findViewById(R.id.constarintLayout);

            //this.onNoteListner = onNoteListner;
        }
        public void bind(){
            int position = getAdapterPosition();
            Commitee_Profile com_posi = commitiesnames.get(position);
            text_com_name.setText(com_posi.getCommit_name());
            if(com_posi.getCommit_img()==null){
                //Toast.makeText(context, "Could not upload image currently...", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onClick(View view) {
            onNoteListner.onNoteClick(getAdapterPosition());                            //This gets the exact position of the point where there was a click...
        }
    }

    public interface OnNoteListner{
        void onNoteClick(int position);
    }
}
