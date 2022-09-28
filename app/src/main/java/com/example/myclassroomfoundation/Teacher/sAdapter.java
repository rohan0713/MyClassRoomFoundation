package com.example.myclassroomfoundation.Teacher;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myclassroomfoundation.R;
import com.example.myclassroomfoundation.tQuestions;
import com.example.myclassroomfoundation.teacher_meeting_list;

import java.util.List;

public class sAdapter extends RecyclerView.Adapter<sAdapter.ViewHolder> {

    List<assignmentModel> assignmentModels;
    int i;
    public sAdapter(List<assignmentModel> assignmentModels, int i){
        this.assignmentModels = assignmentModels;
        this.i = i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.t_subject, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(assignmentModels.get(position));
    }

    @Override
    public int getItemCount() {
        return assignmentModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(assignmentModel assignmentModel) {

            Button find = itemView.findViewById(R.id.view);
            TextView subject = itemView.findViewById(R.id.subject);
            TextView section = itemView.findViewById(R.id.section);
            TextView class_name = itemView.findViewById(R.id.standard);

            subject.setText(assignmentModel.getSubject_name());
            section.setText("Section - " + assignmentModel.getSection_name());
            class_name.setText("Class - " + assignmentModel.getClass_name() + "th");

            find.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (i == 1) {
                        Intent i = new Intent(view.getContext(), tQuestions.class);
                        Log.d("id", assignmentModel.getSub_id());
                        i.putExtra("subject_id", assignmentModel.getSub_id());
                        view.getContext().startActivity(i);
                    } else {
                        Intent i = new Intent(view.getContext(), teacher_meeting_list.class);
                        i.putExtra("id", assignmentModel.getSub_id());
                        i.putExtra("name", assignmentModel.getSubject_name());
                        view.getContext().startActivity(i);
                    }
                }
            });
        }

    }
}
