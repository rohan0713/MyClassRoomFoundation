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

public class qAdapter extends RecyclerView.Adapter<qAdapter.ViewHolder> {

    List<questionModel> ques;
    String sub_id;
    public qAdapter(List<questionModel> ques, String sub_id){
        this.ques = ques;
        this.sub_id = sub_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(ques.get(position));
    }

    @Override
    public int getItemCount() {
        return ques.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(questionModel questionModel) {
            Button view = itemView.findViewById(R.id.view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(view.getContext(), marksActivity.class);
                    i.putExtra("sub_id", sub_id);
                    i.putExtra("ques_id", questionModel.getQuestion_id());
                    i.putExtra("question", questionModel.getQuestion());
                    view.getContext().startActivity(i);
                }
            });

            TextView ques = itemView.findViewById(R.id.question);
            TextView date = itemView.findViewById(R.id.date);
            TextView marks = itemView.findViewById(R.id.marks);
            ques.setText("Q. " + questionModel.getQuestion());
            date.setText("Assigned on : " + questionModel.getQuestion_date());
            marks.setText("Full marks : " + questionModel.getQuestion_marks());
        }
    }
}
