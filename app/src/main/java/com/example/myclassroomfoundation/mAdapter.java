package com.example.myclassroomfoundation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {

    List<answerModel> list;
    String question, subject_id;

    public mAdapter(List<answerModel> list, String ques, String subject_id){
        this.list = list;
        this.question = ques;
        this.subject_id = subject_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_desc, parent, false);
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

        public void bind(answerModel answerModel) {

            TextView name = itemView.findViewById(R.id.name);
            TextView roll = itemView.findViewById(R.id.roll_no);
            name.setText(answerModel.name);
            if(answerModel.roll_no != null){
                roll.setText(answerModel.roll_no);
            }

            Button view = itemView.findViewById(R.id.view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), submitMarks.class);
                    i.putExtra("question", question);
                    i.putExtra("answer", answerModel.answer);
                    i.putExtra("id", answerModel.contact_id);
                    i.putExtra("ques_id", answerModel.question_id);
                    i.putExtra("subject_id", subject_id);
                    view.getContext().startActivity(i);
                }
            });

        }
    }
}
