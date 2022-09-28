package com.example.myclassroomfoundation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myclassroomfoundation.models.loginmodel;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class loginFragment extends Fragment {

    View view;
    String email, password;
    SpinKitView progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        Button login = view.findViewById(R.id.login);
        EditText e = view.findViewById(R.id.email);
        EditText p = view.findViewById(R.id.password);
        progress = view.findViewById(R.id.progress_bar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);
                progress.animate();

                email = e.getText().toString().trim();
                password = p.getText().toString().trim();

                getinfo(email, password);
            }
        });

        return view;
    }

    private void getinfo(String email, String password) {

        Log.d("email", email);
        Log.d("password", password);

        Call<loginmodel> cred = Retrofit.getServices().getRole(email, password);
        cred.enqueue(new Callback<loginmodel>() {
            @Override
            public void onResponse(Call<loginmodel> call, Response<loginmodel> response) {

                progress.setVisibility(View.GONE);
                loginmodel u = response.body();
                assert u != null;
                if(u.isStatus()){
                   String role = u.getRole();
                   String id = u.getId();
                   Log.d("id", id);

                   userHelperClass.put(view.getContext(), "role", role);
                   userHelperClass.put(view.getContext(), "id", id);

                    BottomNavigationView bn = requireActivity().findViewById(R.id.nav_menu);
                    bn.setSelectedItemId(R.id.task);
                    Fragment fragment = new dashboardFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fragment).commit();

                }else{
                    Toast.makeText(view.getContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<loginmodel> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(view.getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}