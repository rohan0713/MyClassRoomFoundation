package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teacher_meeting_list extends AppCompatActivity {

    SpinKitView progress;
    TextView sub;
    String subject_id, subject, url, id;
    TextView subj;
    Button join, create;
    CardView cardView;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_meeting_list);

        progress = findViewById(R.id.progress_bar);
        cardView = findViewById(R.id.card);
        sub = findViewById(R.id.sub_text);
        subj = findViewById(R.id.subject_name);
        join = findViewById(R.id.join);
        create = findViewById(R.id.create);

        subject_id = getIntent().getStringExtra("id");
        subject = getIntent().getStringExtra("name");
        id = (String) userHelperClass.get(teacher_meeting_list.this, "id", "id");

        progress.setVisibility(View.VISIBLE);
        progress.animate();

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(teacher_meeting_list.this, meeting.class);
                i.putExtra("url", url);
                startActivity(i);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(teacher_meeting_list.this, teacher_meeting.class);
                i.putExtra("subject_id", subject_id);
                i.putExtra("contact_id", id);
                startActivityForResult(i,1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        String creat = data.getStringExtra("yes");
        if(creat.equalsIgnoreCase("yes")){
            create.setVisibility(View.GONE);
        }
    }

    private void getList() {

        Call<result> call = Retrofit.getServicesM().joinTeacher(subject_id, id);
        call.enqueue(new Callback<result>() {
            @Override
            public void onResponse(Call<result> call, Response<result> response) {
                progress.setVisibility(View.GONE);
                result r = response.body();

                if(r.status){
                    url = r.url;

                    cardView.setVisibility(View.VISIBLE);
                    join.setVisibility(View.VISIBLE);

                }else{
                    cardView.setVisibility(View.VISIBLE);
                    create.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<result> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(teacher_meeting_list.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getList();
    }
}