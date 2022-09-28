package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myclassroomfoundation.Teacher.assignmentModel;
import com.example.myclassroomfoundation.Teacher.sAdapter;
import com.example.myclassroomfoundation.Teacher.teacher_assignment;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tQuestions extends AppCompatActivity {

    RecyclerView recyclerView;
    Button create;
    String subject_id;
    SpinKitView progress;
    TextView sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tquestions3);
        recyclerView = findViewById(R.id.q_recyclerview);
        create = findViewById(R.id.create);
        progress = findViewById(R.id.progress_bar);
        sub = findViewById(R.id.t1);

        progress.animate();
        subject_id = getIntent().getStringExtra("subject_id");

        List<questionModel> list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (tQuestions.this, LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(tQuestions.this, createAssignment.class);
                i.putExtra("subject_id", subject_id);
                startActivity(i);
            }
        });
    }

    private void getList(String subject_id) {

        Call<List<questionModel>> call = Retrofit.getServices().getQuestions(subject_id);
        call.enqueue(new Callback<List<questionModel>>() {
            @Override
            public void onResponse(Call<List<questionModel>> call, Response<List<questionModel>> response) {

                List<questionModel> list = response.body();
                if(list.size() != 0) {
                    progress.setVisibility(View.GONE);
                    recyclerView.setAdapter(new qAdapter(list, subject_id));
                }else{
                    progress.setVisibility(View.GONE);
                    sub.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<questionModel>> call, Throwable t) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("wth", "Application started");
        getList(subject_id);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("wth", "Application stopped");
    }
}