package com.example.myclassroomfoundation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myclassroomfoundation.Teacher.teacher_assignment;
import com.example.myclassroomfoundation.models.loginmodel;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboardFragment extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View header;
    ImageView menu,close;
    View view;
    String role;
    Button login;
    Boolean flag = false;
    SpinKitView progress;
    String contact_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        role = String.valueOf(userHelperClass.get(requireActivity(), "role", "login"));
        contact_id = (String) userHelperClass.get(requireActivity(), "id", "id");

        flag = (Boolean) userHelperClass.get(requireActivity(), "flag", false);
        Log.d("role", role);

        if(role.equalsIgnoreCase("student")) {
            view = inflater.inflate(R.layout.fragment_dashboard, container, false);
            drawerLayout = view.findViewById(R.id.drawer_layout);
            navigationView = view.findViewById(R.id.nav_view);
            menu = view.findViewById(R.id.menu);

            navigationView.bringToFront();
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            header = navigationView.getHeaderView(0);
            TextView assignment = header.findViewById(R.id.assignment);
            TextView classes = header.findViewById(R.id.text3);
            TextView notification = header.findViewById(R.id.text11);
            TextView report = header.findViewById(R.id.report);

            report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), reportCard.class);
                    startActivity(i);
                }
            });

            notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), notification.class);
                    startActivity(i);
                }
            });

            assignment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), student_assignments.class);
                    startActivity(i);
                }
            });

            classes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), meeting_class.class);
                    startActivity(i);
                }
            });

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (drawerLayout.isDrawerVisible(GravityCompat.END)) {
                        drawerLayout.closeDrawer(GravityCompat.END);
                    } else {
                        drawerLayout.openDrawer(GravityCompat.END);
                    }
                }
            });

            close = header.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                }
            });
        }

        else if(role.equalsIgnoreCase("admin")){
            view = inflater.inflate(R.layout.fragment_admin, container, false);
            menu = view.findViewById(R.id.menu);
            drawerLayout = view.findViewById(R.id.drawer_layout);
            navigationView = view.findViewById(R.id.nav_view);
            Button tutors = view.findViewById(R.id.tutors);

            navigationView.bringToFront();
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            header = navigationView.getHeaderView(0);
            TextView register = header.findViewById(R.id.register);
            TextView subject = header.findViewById(R.id.subject);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), registerActivity.class);
                    startActivity(i);
                }
            });

            tutors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), teacher_list.class);
                    startActivity(i);
                }
            });

            subject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), createSub.class);
                    startActivity(i);
                }
            });

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (drawerLayout.isDrawerVisible(GravityCompat.END)) {
                        drawerLayout.closeDrawer(GravityCompat.END);
                    } else {
                        drawerLayout.openDrawer(GravityCompat.END);
                    }
                }
            });

            close = header.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                }
            });
        }
        else if(role.equalsIgnoreCase("teacher")){

            view = inflater.inflate(R.layout.fragment_teacher, container, false);
            menu = view.findViewById(R.id.menu);
            drawerLayout = view.findViewById(R.id.drawer_layout);
            navigationView = view.findViewById(R.id.nav_view);

            navigationView.bringToFront();
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            header = navigationView.getHeaderView(0);
            TextView assignment = header.findViewById(R.id.assignment);
            TextView classes = header.findViewById(R.id.classes);

            assignment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), teacher_assignment.class);
                    startActivity(i);
                }
            });

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (drawerLayout.isDrawerVisible(GravityCompat.END)) {
                        drawerLayout.closeDrawer(GravityCompat.END);
                    } else {
                        drawerLayout.openDrawer(GravityCompat.END);
                    }
                }
            });

            classes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), teacher_meeting_class.class);
                    startActivity(i);
                }
            });

            close = header.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                }
            });

        }else{
            view = inflater.inflate(R.layout.login_prompt, container, false);
            login = view.findViewById(R.id.login);
            EditText email = view.findViewById(R.id.email);
            EditText password = view.findViewById(R.id.password);
            progress = view.findViewById(R.id.progress_bar);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String e = email.getText().toString().trim();
                    String p = password.getText().toString().trim();

                    progress.setVisibility(View.VISIBLE);
                    progress.animate();
                    getinfo(e, p);
                }
            });
        }
        return view;
    }

    private void getinfo(String email, String password) {

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