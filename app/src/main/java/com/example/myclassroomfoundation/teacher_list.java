package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teacher_list extends AppCompatActivity {

    RecyclerView recyclerView;
    SpinKitView progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        recyclerView = findViewById(R.id.q_recyclerview);
        progress_bar = findViewById(R.id.progress_bar);

        progress_bar.animate();

        List<teacherModel> list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (teacher_list.this, LinearLayoutManager.VERTICAL,false);

        getList();
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getList() {

        Call<List<teacherModel>> call = Retrofit.getServices().getTeachers();
        call.enqueue(new Callback<List<teacherModel>>() {
            @Override
            public void onResponse(Call<List<teacherModel>> call, Response<List<teacherModel>> response) {

                List<teacherModel> list = response.body();
                progress_bar.setVisibility(View.GONE);
                recyclerView.setAdapter(new teacherAdapter(list));
            }

            @Override
            public void onFailure(Call<List<teacherModel>> call, Throwable t) {

            }
        });

    }
}