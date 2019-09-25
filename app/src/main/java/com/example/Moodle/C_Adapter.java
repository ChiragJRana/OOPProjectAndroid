package com.example.Moodle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Moodle.Commitiesname;
import com.example.Moodle.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class C_Adapter extends RecyclerView.Adapter<C_Adapter.ChatViewHolder>{
    private List<Commitiesname> commitiesnames;
    private LayoutInflater layoutInflater;
    private Context context;
    private final String Tag="C_Adapter";
    private OnNoteListner onNoteListner;

    public C_Adapter(Context context,OnNoteListner onNoteListner){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        commitiesnames = new ArrayList<>();
        this.onNoteListner = onNoteListner;
    }
    public void setData(List<Commitiesname> commitiesnames){
        this.commitiesnames.clear();
        this.commitiesnames.addAll(commitiesnames);
        notifyDataSetChanged();
    }
    public void addData(List<Commitiesname> commitiesnames){
        this.commitiesnames.addAll(commitiesnames);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.commities_name,parent,false);
        Log.e(TAG,"OnCreateViewHolder");
        return new ChatViewHolder(view,onNoteListner);
    }

    @Override
    public void onBindViewHolder(@NonNull C_Adapter.ChatViewHolder holder, int position) {
        Log.e(TAG,"OnBindViewHolder");
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return commitiesnames.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView text_com_name;
        OnNoteListner onNoteListner;

        public ChatViewHolder(@NonNull View view,OnNoteListner onNoteListner){
            super(view);
            imageView = view.findViewById(R.id.com_logo);
            text_com_name = view.findViewById(R.id.com_name);
            this.onNoteListner = onNoteListner;
        }
        public void bind(){
            int position = getAdapterPosition();
            Commitiesname com_posi = commitiesnames.get(position);
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
