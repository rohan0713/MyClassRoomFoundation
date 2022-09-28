package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class meetingActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String link = "";
    boolean flag = false;
    SpinKitView progress;
    TextView sub;
    String subject_id, subject, url, id;
    TextView subj;
    Button join, create;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

        List<meetingModel> list = new ArrayList<>();
        progress = findViewById(R.id.progress_bar);
        cardView = findViewById(R.id.card);
        sub = findViewById(R.id.sub_text);
        subj = findViewById(R.id.subject_name);
        join = findViewById(R.id.join);
        create = findViewById(R.id.create);

        subject_id = getIntent().getStringExtra("id");
        subject = getIntent().getStringExtra("name");
        id = (String) userHelperClass.get(meetingActivity.this, "id", "id");

        progress.animate();
        getList();

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(meetingActivity.this, meeting.class);
                i.putExtra("url", url);
                startActivity(i);
            }
        });
    }

    private void getList() {

        Call<result> call = Retrofit.getServicesM().joinMeeting(subject_id, id);
        progress.setVisibility(View.GONE);
        call.enqueue(new Callback<result>() {
            @Override
            public void onResponse(Call<result> call, Response<result> response) {
                result r = response.body();
                if(r.status){
                    url = r.url;
                    cardView.setVisibility(View.VISIBLE);
                }else{
                    sub.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<result> call, Throwable t) {

            }
        });
    }
}