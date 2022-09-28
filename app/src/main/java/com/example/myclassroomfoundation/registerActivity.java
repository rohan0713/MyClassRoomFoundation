package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registerActivity extends AppCompatActivity {

    Spinner role, class_name, section;
    List<classImodel> list = new ArrayList<>();
    List<String> list1 = new ArrayList<>();

    HashMap<String, String> map = new HashMap<>();
    HashMap<String, String> smap = new HashMap<>();
    HashMap<String, String> tmap = new HashMap<>();
    TextView class_t, section_t;
    ArrayAdapter<String> adapter, adapter1;

    EditText first_name, last_name, password, dob, email, phone, address1,
            address2, city,state, country, pincode;
    String s_id, c_id, r;
    Button signUp;
    SpinKitView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        class_name = findViewById(R.id.class_name);
        section = findViewById(R.id.section);
        role = findViewById(R.id.role);
        class_t = findViewById(R.id.text9);
        section_t = findViewById(R.id.text10);
        progress = findViewById(R.id.progress_bar);

        first_name = findViewById(R.id.f_name);
        last_name = findViewById(R.id.l_name);
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        country = findViewById(R.id.country);
        pincode = findViewById(R.id.pincode);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        registerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + day;
                        dob.setText(date);
                    }
                }, year, month, day
                );
                datePickerDialog.show();
            }
        });

        signUp = findViewById(R.id.signUp);

        getlist();
       adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                list1);

        class_name.setAdapter(adapter);

        class_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String c = adapterView.getItemAtPosition(i).toString();
                c_id = map.get(c);
                Call<List<sectionModel>> call = Retrofit.getServices().getSections(c_id);
                call.enqueue(new Callback<List<sectionModel>>() {
                    @Override
                    public void onResponse(Call<List<sectionModel>> call, Response<List<sectionModel>> response) {

                        List<sectionModel> list = response.body();
                        List<String> list2 = new ArrayList<>();

                        for(int i = 0; i< (list != null ? list.size() : 0); i++){
                            list2.add(list.get(i).section_name);
                            smap.put(list.get(i).section_name, list.get(i).section_id);
                        }

                        adapter1 = new ArrayAdapter<String>(registerActivity.this,
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
                String c = adapterView.getItemAtPosition(i).toString();
                s_id = smap.get(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                r = adapterView.getItemAtPosition(i).toString();
                if(r.equalsIgnoreCase("student")){

                    class_t.setVisibility(View.VISIBLE);
                    class_name.setVisibility(View.VISIBLE);

                    section_t.setVisibility(View.VISIBLE);
                    section.setVisibility(View.VISIBLE);

                }else {
                    class_t.setVisibility(View.GONE);
                    class_name.setVisibility(View.GONE);

                    section_t.setVisibility(View.GONE);
                    section.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.animate();
                Log.d("class_id", c_id);
                Log.d("section_id", s_id);

                String f_name = first_name.getText().toString().trim();
                String l_name = last_name.getText().toString().trim();
                String e = email.getText().toString().trim();
                String p = password.getText().toString().trim();
                String d = dob.getText().toString().trim();
                String no = phone.getText().toString().trim();
                String a1 = address1.getText().toString().trim();
                String a2 = address2.getText().toString().trim();
                String c = city.getText().toString().trim();
                String s = state.getText().toString().trim();
                String cn = country.getText().toString().trim();
                String pc = pincode.getText().toString().trim();

                Call<result> call = Retrofit.getServices().register(f_name, l_name, e, p, d, no,
                a1, a2, c, s, cn, pc, r, s_id, c_id);

                call.enqueue(new Callback<result>() {
                    @Override
                    public void onResponse(Call<result> call, Response<result> response) {

                        result rt = response.body();
                        if(rt.status){
                            Toast.makeText(registerActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(registerActivity.this, "Something wrong! Try again", Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<result> call, Throwable t) {
                        Toast.makeText(registerActivity.this, "Check the internet connectivity", Toast.LENGTH_SHORT).show();
                        progress.setVisibility(View.GONE);
                    }
                });


            }
        });
    }

    private void getlist() {

        Call<List<classImodel>> call = Retrofit.getServices().getClasses();
        call.enqueue(new Callback<List<classImodel>>() {
            @Override
            public void onResponse(Call<List<classImodel>> call, Response<List<classImodel>> response) {

                list = response.body();
                for(int i = 0; i< (list != null ? list.size() : 0); i++){
                    list1.add(list.get(i).class_name);
                    map.put(list.get(i).class_name, list.get(i).class_id);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<classImodel>> call, Throwable t) {

            }
        });

    }

}