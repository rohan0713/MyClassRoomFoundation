package com.example.myclassroomfoundation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myclassroomfoundation.Teacher.assignmentModel;

import java.util.List;

public class subAdapter extends RecyclerView.Adapter<subAdapter.ViewHolder> {

    List<assignmentModel> list;
    int flag;

    public subAdapter(List<assignmentModel> list, int flag) {
        this.list = list;
        this.flag = flag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assign_card, parent, false);
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

        public void bind(assignmentModel assignmentModel) {

            TextView sub = itemView.findViewById(R.id.subjects);
            sub.setText(assignmentModel.getSubject_name());

            if (flag == 1) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), report.class);
                        i.putExtra("id", assignmentModel.getSub_id());
                        view.getContext().startActivity(i);
                    }
                });
            } else if (flag == 2) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), sComplete.class);
                        i.putExtra("id", assignmentModel.getSub_id());
                        view.getContext().startActivity(i);
                    }
                });
            } else if (flag == 3) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), meetingActivity.class);
                        i.putExtra("id", assignmentModel.getSub_id());
                        i.putExtra("name", assignmentModel.getSubject_name());
                        view.getContext().startActivity(i);
                    }
                });
            } else{

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), teacher_meeting_list.class);
                        i.putExtra("id", assignmentModel.getSub_id());
                        i.putExtra("name", assignmentModel.getSubject_name());
                        view.getContext().startActivity(i);
                    }
                });

            }
        }
    }
}
