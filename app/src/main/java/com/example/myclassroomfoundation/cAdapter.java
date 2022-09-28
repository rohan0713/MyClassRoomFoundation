package com.example.myclassroomfoundation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cAdapter extends RecyclerView.Adapter<cAdapter.cViewHolder> {

    List<classModel> list;

    public cAdapter(List<classModel> list) {
       this.list = list;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);
        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        holder.Bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class cViewHolder extends RecyclerView.ViewHolder {
        public cViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void Bind(classModel classModel) {

            ImageView imageView;
            TextView s, c;

            imageView = itemView.findViewById(R.id.cImage);
            s = itemView.findViewById(R.id.section);
            c = itemView.findViewById(R.id.classes);

            imageView.setBackgroundResource(classModel.getImage());
            s.setText(classModel.getSection());
            c.setText(classModel.getClasses());

        }
    }
}
