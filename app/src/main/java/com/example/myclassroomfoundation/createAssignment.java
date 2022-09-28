package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createAssignment extends AppCompatActivity {

    EditText ques, marks;
    Button create;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assignment);
        
        ques = findViewById(R.id.question);
        marks = findViewById(R.id.marks);
        create = findViewById(R.id.create);
        String subject_id = getIntent().getStringExtra("subject_id");
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = ques.getText().toString().trim();
                String m = marks.getText().toString().trim();

                if(q.length() != 0 && m.length() != 0) {
                    Call<result> call = Retrofit.getServices().createAssignment(q, "", m,
                            subject_id);
                    call.enqueue(new Callback<result>() {
                        @Override
                        public void onResponse(Call<result> call, Response<result> response) {
                            result r = response.body();
                            assert r != null;
                            if(r.status){
                                finish();
                            }else{
                                Toast.makeText(createAssignment.this, "Something wrong! Try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<result> call, Throwable t) {
                            Toast.makeText(createAssignment.this, "failed", Toast.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Toast.makeText(createAssignment.this, "Give all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}