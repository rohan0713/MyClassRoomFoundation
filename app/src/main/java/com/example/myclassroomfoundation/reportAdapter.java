package com.example.myclassroomfoundation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class reportAdapter extends RecyclerView.Adapter<reportAdapter.ViewHolder> {

    List<report_result> list;
    public reportAdapter(List<report_result> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marks_card, parent, false);
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

        public void bind(report_result report_result) {
            TextView question = itemView.findViewById(R.id.question);
            TextView answer = itemView.findViewById(R.id.answer);
            TextView om = itemView.findViewById(R.id.om);
            TextView fm = itemView.findViewById(R.id.fm);

            question.setText("Q. " + report_result.question);
            answer.setText("Ans. " + report_result.answer);
            om.setText("Obtained Marks : " + report_result.obtained_mark);
            fm.setText("Full Marks : " + report_result.full_mark);
        }
    }
}
