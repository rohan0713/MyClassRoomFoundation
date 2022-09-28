package com.example.myclassroomfoundation.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myclassroomfoundation.R;
import com.example.myclassroomfoundation.Retrofit;
import com.example.myclassroomfoundation.userHelperClass;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teacher_assignment extends AppCompatActivity {

    RecyclerView recyclerView;
    SpinKitView pr;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_assignment);
        recyclerView = findViewById(R.id.sub_recyclerview);
        pr = findViewById(R.id.progress_bar);
        t = findViewById(R.id.t1);

        pr.animate();
        List<assignmentModel> list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (teacher_assignment.this, LinearLayoutManager.VERTICAL,false);

        getList();

        recyclerView.setLayoutManager(layoutManager);
    }

    private void getList() {

        String id = String.valueOf(userHelperClass.get(teacher_assignment.this,
                "id", "id"));

        Call<List<assignmentModel>> sub = Retrofit.getServices().getSubjects(id);
        sub.enqueue(new Callback<List<assignmentModel>>() {
            @Override
            public void onResponse(Call<List<assignmentModel>> call, Response<List<assignmentModel>> response) {

                List<assignmentModel> list = response.body();
                assert list != null;
                if (list.size() != 0) {
                    pr.setVisibility(View.GONE);
                    recyclerView.setAdapter(new sAdapter(list, 1));
                }else{
                    pr.setVisibility(View.GONE);
                    t.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<assignmentModel>> call, Throwable t) {

            }
        });
    }
}