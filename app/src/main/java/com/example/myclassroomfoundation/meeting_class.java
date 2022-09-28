package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myclassroomfoundation.Teacher.assignmentModel;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class meeting_class extends AppCompatActivity {

    RecyclerView recyclerView;
    String id;
    SpinKitView progress;
    TextView subj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_class);

        progress = findViewById(R.id.progress_bar);
        subj = findViewById(R.id.sub_text);
        progress.animate();

        List<studentModel> list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        id = (String) userHelperClass.get(meeting_class.this, "id", "id");
        Log.d("id", id);
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (meeting_class.this, LinearLayoutManager.VERTICAL,false);

        getList();

        recyclerView.setLayoutManager(layoutManager);
    }

    private void getList() {

        Call<List<assignmentModel>> call = Retrofit.getServices().getStudentSubject(id);
        call.enqueue(new Callback<List<assignmentModel>>() {
            @Override
            public void onResponse(Call<List<assignmentModel>> call, Response<List<assignmentModel>> response) {
                List<assignmentModel> sub = response.body();
                assert sub != null;
                if (sub.size() != 0) {
                    progress.setVisibility(View.GONE);
                    recyclerView.setAdapter(new subAdapter(sub, 3));
                }else{
                    progress.setVisibility(View.GONE);
                    subj.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<assignmentModel>> call, Throwable t) {

            }
        });
    }
}