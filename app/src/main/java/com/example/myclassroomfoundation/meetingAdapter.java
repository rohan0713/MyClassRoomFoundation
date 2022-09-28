package com.example.myclassroomfoundation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myclassroomfoundation.Teacher.sAdapter;

import java.util.List;

public class meetingAdapter extends RecyclerView.Adapter<meetingAdapter.ViewHolder> {

    List<meetingModel> list;
    public meetingAdapter(List<meetingModel> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(meetingModel meetingModel) {

            Button create = itemView.findViewById(R.id.create);
            Button join = itemView.findViewById(R.id.join);

            if(meetingModel.link.length() != 0){
                create.setVisibility(View.GONE);
            }

            create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), teacher_meeting.class);
                    view.getContext().startActivity(i);
                }
            });

            join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), meeting.class);
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}
