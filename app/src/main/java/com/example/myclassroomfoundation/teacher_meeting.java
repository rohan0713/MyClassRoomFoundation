package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teacher_meeting extends AppCompatActivity {

    EditText meeting, moderator_pw, attendee_pw, message;
    String subject_id, teacher_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_meeting);

        meeting = findViewById(R.id.email);
        moderator_pw = findViewById(R.id.address1);
        attendee_pw = findViewById(R.id.phone);
        message = findViewById(R.id.welcome);

        subject_id = getIntent().getStringExtra("subject_id");
        teacher_id = getIntent().getStringExtra("contact_id");

        Button create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createM();
            }
        });
    }

    private void createM() {

        Call<result> call = Retrofit.getServicesM().createMeeting(
                meeting.getText().toString().trim(),
                attendee_pw.getText().toString().trim(),
                moderator_pw.getText().toString().trim(),
                message.getText().toString().trim(),
                subject_id, teacher_id);
        call.enqueue(new Callback<result>() {
            @Override
            public void onResponse(Call<result> call, Response<result> response) {

                result r = response.body();
                if(r.status){
                    Toast.makeText(teacher_meeting.this, "Meeting Created", Toast.LENGTH_SHORT).show();
                    Intent previousScreen = new Intent(getApplicationContext(), teacher_meeting_list.class);
                    //Sending the data to Activity_A
                    previousScreen.putExtra("yes"," yes");
                    setResult(1000, previousScreen);
                    finish();
                }else{
                    Toast.makeText(teacher_meeting.this, "Something wrong! Try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<result> call, Throwable t) {
                Toast.makeText(teacher_meeting.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}