package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myclassroomfoundation.Teacher.assignmentModel;
import com.example.myclassroomfoundation.Teacher.sAdapter;
import com.example.myclassroomfoundation.Teacher.teacher_assignment;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class marksActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String subject_id, question, contact_id, ques_id;
    SpinKitView progress;
    TextView sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        recyclerView = findViewById(R.id.m_recyclerview);

        progress = findViewById(R.id.progress_bar);
        sub = findViewById(R.id.sub_text);

        progress.animate();
        contact_id = (String) userHelperClass.get(marksActivity.this, "id", "id");
        subject_id = getIntent().getStringExtra("sub_id");
        question = getIntent().getStringExtra("question");
        ques_id = getIntent().getStringExtra("ques_id");

        List<studentModel> list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (marksActivity.this, LinearLayoutManager.VERTICAL,false);

        getList();
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getList() {

        Call<List<answerModel>> call = Retrofit.getServices().getAnswers(subject_id, ques_id);
        call.enqueue(new Callback<List<answerModel>>() {
            @Override
            public void onResponse(Call<List<answerModel>> call, Response<List<answerModel>> response) {
                List<answerModel> list = response.body();
                if(list.size() != 0) {
                    progress.setVisibility(View.GONE);
                    recyclerView.setAdapter(new mAdapter(list, question, subject_id));
                }else{
                    progress.setVisibility(View.GONE);
                    sub.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<answerModel>> call, Throwable t) {

            }
        });
    }
}