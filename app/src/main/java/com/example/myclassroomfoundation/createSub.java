package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createSub extends AppCompatActivity {


    Spinner classes, section, teacher;
    EditText subject;
    List<String> list = new ArrayList<>();
    List<String> t_list = new ArrayList<>();
    HashMap<String, String> map, smap, tmap;
    ArrayAdapter<String> adapter, adapter1, adapter2;
    String class_id, section_id, teacher_id, subject_name;

    String class_name = "";
    String section_name = "";
    String teacher_name = "";
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sub);

        subject = findViewById(R.id.f_name);
        classes = findViewById(R.id.classes);
        section = findViewById(R.id.section);
        teacher = findViewById(R.id.teacher_name);
        create = findViewById(R.id.create);

        getList();
        getTeacher();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                list);

        adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                t_list);

        classes.setAdapter(adapter);
        teacher.setAdapter(adapter2);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String subject_name = subject.getText().toString().trim();
                if(subject_name.length() != 0 &&
                class_name.length()!= 0 && section_name.length()!=0 && teacher_name.length()!=0){

                    String class_id = map.get(class_name);
                    String section_id = smap.get(section_name);
                    String teacher_id = tmap.get(teacher_name);

                    Log.d("class_id", class_id);
                    Log.d("section_id", section_id);
                    Log.d("teacher_id", teacher_id);

                    Call<result> call = Retrofit.getServices().createSubject(subject_name,
                            class_id, section_id, teacher_id);

                    call.enqueue(new Callback<result>() {
                        @Override
                        public void onResponse(Call<result> call, Response<result> response) {

                            result r = response.body();
                            assert r != null;
                            if(r.status){
                                Toast.makeText(createSub.this, "Subject Created", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(createSub.this, "Something wrong! Try again", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<result> call, Throwable t) {
                            Toast.makeText(createSub.this, "Check the internet connection", Toast.LENGTH_SHORT).show();

                        }
                    });


                }else{
                    Toast.makeText(createSub.this, "Give all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });


        classes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                class_name = adapterView.getItemAtPosition(i).toString();
                String class_id = map.get(class_name);

                Call<List<sectionModel>> call = Retrofit.getServices().getSections(class_id);
                call.enqueue(new Callback<List<sectionModel>>() {
                    @Override
                    public void onResponse(Call<List<sectionModel>> call, Response<List<sectionModel>> response) {
                        List<sectionModel> list = response.body();
                        List<String> list2 = new ArrayList<>();
                        smap = new HashMap<>();

                        for (int i = 0; i < (list != null ? list.size() : 0); i++) {
                            list2.add(list.get(i).section_name);
                            smap.put(list.get(i).section_name, list.get(i).section_id);
                        }

                        adapter1 = new ArrayAdapter<String>(createSub.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                list2);
                        adapter1.notifyDataSetChanged();
                        section.setAdapter(adapter1);
                    }

                    @Override
                    public void onFailure(Call<List<sectionModel>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                section_name = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        teacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                teacher_name = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getTeacher() {

        Call<List<teacherModel>> call = Retrofit.getServices().getTeachers();
        call.enqueue(new Callback<List<teacherModel>>() {
            @Override
            public void onResponse(Call<List<teacherModel>> call, Response<List<teacherModel>> response) {
                List<teacherModel> l = response.body();
                tmap = new HashMap<>();
                for (int i = 0; i < l.size(); i++){
                    t_list.add(l.get(i).teacher_name);
                    tmap.put(l.get(i).teacher_name, l.get(i).teacher_id);
                    adapter2.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<teacherModel>> call, Throwable t) {

            }
        });
    }

    private void getList() {

        Call<List<classImodel>> call = Retrofit.getServices().getClasses();
        call.enqueue(new Callback<List<classImodel>>() {
            @Override
            public void onResponse(Call<List<classImodel>> call, Response<List<classImodel>> response) {

                List<classImodel> l = response.body();
                map = new HashMap<>();
                for (int i = 0; i < l.size(); i++) {
                    list.add(l.get(i).class_name);
                    map.put(l.get(i).class_name, l.get(i).class_id);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<classImodel>> call, Throwable t) {

            }
        });
    }
}