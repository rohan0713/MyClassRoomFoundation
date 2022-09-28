package com.example.myclassroomfoundation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class assignAdapter extends RecyclerView.Adapter<assignAdapter.ViewHolder> {

    List<questionModel> list;
    String subject_id;

    public assignAdapter(List<questionModel> list, String subject_id){
        this.list = list;
        this.subject_id = subject_id;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.s_questions, parent, false);
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

        public void bind(questionModel questionModel) {

            TextView q = itemView.findViewById(R.id.question);
            q.setText("Q. " + questionModel.getQuestion());

            Button submit = itemView.findViewById(R.id.submit);
            EditText ans = itemView.findViewById(R.id.answer);

            String contact_id = (String) userHelperClass.get(itemView.getContext(),
                    "id", "id");
            String ques_id = questionModel.getQuestion_id();

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String answer = ans.getText().toString().trim();
                    if(answer.length() != 0){

                        Call<result> call = Retrofit.getServices().submitAssignments(subject_id,
                                answer, "", ques_id, contact_id);
                        call.enqueue(new Callback<result>() {
                            @Override
                            public void onResponse(Call<result> call, Response<result> response) {

                                result r = response.body();
                                if(r.status){
                                    Toast.makeText(view.getContext(), "Answer Submitted",
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(view.getContext(), "Something wrong! Try again",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<result> call, Throwable t) {
                                Toast.makeText(view.getContext(), "Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else{
                        Toast.makeText(view.getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
