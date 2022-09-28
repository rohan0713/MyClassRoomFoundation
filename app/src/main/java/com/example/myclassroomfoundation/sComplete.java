package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sComplete extends AppCompatActivity {

    RecyclerView recyclerView;
    String subject_id;
    SpinKitView progress;
    TextView s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scomplete);

        progress = findViewById(R.id.progress_bar);
        progress.animate();
        s = findViewById(R.id.status);

        List<studentModel> list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (sComplete.this, LinearLayoutManager.VERTICAL,false);

        subject_id = getIntent().getStringExtra("id");

        getlist();
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getlist() {

        Call<List<questionModel>> call = Retrofit.getServices().getQuestions(subject_id);
        call.enqueue(new Callback<List<questionModel>>() {
            @Override
            public void onResponse(Call<List<questionModel>> call, Response<List<questionModel>> response) {

                List<questionModel> list = response.body();
                if (list.size() != 0) {
                    progress.setVisibility(View.GONE);
                    recyclerView.setAdapter(new assignAdapter(list, subject_id));
                }else{
                    progress.setVisibility(View.GONE);
                    s.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<questionModel>> call, Throwable t) {

            }
        });
    }
}