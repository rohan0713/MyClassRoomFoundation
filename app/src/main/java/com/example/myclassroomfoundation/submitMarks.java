package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class submitMarks extends AppCompatActivity {

    String contact_id, subject_id, question_id;
    TextView name;
    TextView standard;

    EditText marks;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_marks);
        name = findViewById(R.id.name);
        standard = findViewById(R.id.section);
        marks = findViewById(R.id.marks);
        submit = findViewById(R.id.submit);

        contact_id = getIntent().getStringExtra("id");
        subject_id = getIntent().getStringExtra("subject_id");
        question_id = getIntent().getStringExtra("ques_id");

        getUser();
        TextView ques = findViewById(R.id.question);
        TextView ans = findViewById(R.id.answer);

        ques.setText("Q. " + getIntent().getStringExtra("question"));
        ans.setText(getIntent().getStringExtra("answer"));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String m = marks.getText().toString().trim();
                if(m.length() != 0){

                    Call<result> call = Retrofit.getServices().assignMarks(question_id,
                            subject_id, contact_id, m);
                    call.enqueue(new Callback<result>() {
                        @Override
                        public void onResponse(Call<result> call, Response<result> response) {
                            result r = response.body();
                            assert r != null;
                            if(r.status){
                                Toast.makeText(submitMarks.this, "marks submitted", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(submitMarks.this, "marks not submitted", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<result> call, Throwable t) {
                            Toast.makeText(submitMarks.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(submitMarks.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getUser() {
        Call<user> call = Retrofit.getServices().getUserDetails(contact_id);
        call.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {

                user u = response.body();
                assert u != null;
                name.setText(u.name);
                standard.setText(u.section);
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {

            }
        });
    }
}