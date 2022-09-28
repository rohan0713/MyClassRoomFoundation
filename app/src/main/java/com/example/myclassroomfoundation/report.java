package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class report extends AppCompatActivity {

    RecyclerView recyclerView;
    String subject_id, contact_id;
    SpinKitView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        recyclerView = findViewById(R.id.q_recyclerview);
        progress = findViewById(R.id.progress_bar);

        LinearLayoutManager layoutManager = new LinearLayoutManager
                (report.this, LinearLayoutManager.VERTICAL,false);

        subject_id = getIntent().getStringExtra("id");
        contact_id = (String) userHelperClass.get(report.this, "id", "id");

        recyclerView.setLayoutManager(layoutManager);

        getList();
    }

    private void getList() {

        Call<List<report_result>> call = Retrofit.getServices().assignmentMarks(subject_id, contact_id);
        call.enqueue(new Callback<List<report_result>>() {
            @Override
            public void onResponse(Call<List<report_result>> call, Response<List<report_result>> response) {

                progress.setVisibility(View.GONE);
                List<report_result> list = response.body();
                recyclerView.setAdapter(new reportAdapter(list));
            }

            @Override
            public void onFailure(Call<List<report_result>> call, Throwable t) {

            }
        });

    }
}