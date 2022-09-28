package com.example.myclassroomfoundation;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profileFragment extends Fragment {

    ImageView menu, close;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View header;

    ConstraintLayout layout;
    TextView name, email, class_s, role;
    EditText mobile, classes, dob, pin, address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        menu = view.findViewById(R.id.menu);
        name = view.findViewById(R.id.text1);
        email = view.findViewById(R.id.text2);
        class_s = view.findViewById(R.id.text3);
        layout = view.findViewById(R.id.layout4);

        mobile = view.findViewById(R.id.mobile);
        classes = view.findViewById(R.id.classes);
        dob = view.findViewById(R.id.dob);
        pin = view.findViewById(R.id.pin);
        address = view.findViewById(R.id.address);

        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);

        String id = (String) userHelperClass.get(view.getContext(), "id", "id");
        getUser(id);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        header = navigationView.getHeaderView(0);
        close = header.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.END);
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

        TextView log = header.findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHelperClass.clear(view.getContext());
                BottomNavigationView bn = requireActivity().findViewById(R.id.nav_menu);
                bn.setSelectedItemId(R.id.home);
                Fragment fragment = new homeFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment).commit();
            }
        });
        return view;
    }

    private void getUser(String id) {

        Call<user> call = Retrofit.getServices().getProfile(id);
        call.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {

                user s = response.body();
                if(s.status){
//                    layout.setVisibility(View.VISIBLE);

                    name.setText(s.name + " | " + s.role);
                    email.setText(s.email);
                    class_s.setText("Class " + s.class_name + " | Section" + s.section);
                    mobile.setText(s.phone_no);
                    classes.setText("Class " + s.class_name);
                    dob.setText(s.date_of_birth);
                    pin.setText(s.pincode);
                    address.setText(s.address + "," + s.city + "," + s.state + "," + s.country);

                }else{
                    Toast.makeText(requireActivity(), "Something wrong! Try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {

            }
        });
    }
}