package com.example.myclassroomfoundation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class homeFragment extends Fragment {

    RecyclerView recyclerView, recyclerView2, recyclerView3;
    ImageView menu,close;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View header;
    Button dash;
    String role;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView2 = view.findViewById(R.id.recyclerview2);
        recyclerView3 = view.findViewById(R.id.recyclerview3);

        menu = view.findViewById(R.id.menu);
        role = String.valueOf(userHelperClass.get(requireActivity(), "role", "login"));

        dash = view.findViewById(R.id.dash);
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomNavigationView bn = requireActivity().findViewById(R.id.nav_menu);
                bn.setSelectedItemId(R.id.task);
                Fragment fragment = new dashboardFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment).commit();
            }
        });
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        header = navigationView.getHeaderView(0);

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
        List<classModel> list = new ArrayList<>();
        List<classModel> list1 = new ArrayList<>();
        List<classModel> list2 = new ArrayList<>();
        list.add(new classModel(R.drawable.why, "Primary", "Nursery/kg to 5th"));
        list.add(new classModel(R.drawable.why, "Middle", "6th to 10th"));
        list.add(new classModel(R.drawable.why, "High", "11th and 12th"));


        list1.add(new classModel(R.drawable.why, "Customised Worksheets", "Get customised worksheets as per your curriculum"));
        list1.add(new classModel(R.drawable.why, "Stay Updated", "Get the latest news and updates"));
        list1.add(new classModel(R.drawable.why, "Test Yourself", "Analyse"));

        list2.add(new classModel(R.drawable.why, "Free Resources", "Access all the necessary academeic resources like N.C.E.R.T Solutions, Exemplar Solutions, Worksheets, Assignments for free!"));
        list2.add(new classModel(R.drawable.why, "Buddy At Your Doorstep", "My Study Buddy also provides Tutor on-demand. buddy at your doorstep can help your child overcome any Academic Obstacle."));

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager1);
        recyclerView3.setLayoutManager(layoutManager2);

        cAdapter adapter = new cAdapter(list);
        cardAdapter adapter1 = new cardAdapter(list1);
        cardAdapter2 adapter2 = new cardAdapter2(list2);

        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter1);
        recyclerView3.setAdapter(adapter2);

        //for dots indicator
        final int radius = getResources().getDimensionPixelSize(R.dimen.radius);
        final int dotsHeight = getResources().getDimensionPixelSize(R.dimen.dots_height);
        final int color = ContextCompat.getColor(view.getContext(), R.color.white);
        final int color1 = ContextCompat.getColor(view.getContext(), R.color.black);

        recyclerView.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, color, color));
        recyclerView2.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, color1, color1));
        recyclerView3.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, color1, color1));

        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        new PagerSnapHelper().attachToRecyclerView(recyclerView2);
        new PagerSnapHelper().attachToRecyclerView(recyclerView3);

        //for auto scrolling
        recyclerView.setOnFlingListener(null);
        recyclerView2.setOnFlingListener(null);
        recyclerView3.setOnFlingListener(null);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        snapHelper.attachToRecyclerView(recyclerView2);
        snapHelper.attachToRecyclerView(recyclerView3);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (layoutManager.findLastVisibleItemPosition() < (adapter.getItemCount() - 1)){
                    layoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), layoutManager.findLastCompletelyVisibleItemPosition() + 1);
                }else{
                    layoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
                }
            }
        }, 0, 3000);

        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (layoutManager1.findLastVisibleItemPosition() < (adapter1.getItemCount() - 1)){
                    layoutManager1.smoothScrollToPosition(recyclerView2, new RecyclerView.State(), layoutManager1.findLastCompletelyVisibleItemPosition() + 1);
                }else{
                    layoutManager1.smoothScrollToPosition(recyclerView2, new RecyclerView.State(), 0);
                }
            }
        }, 0, 4000);

        Timer timer2 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (layoutManager2.findLastVisibleItemPosition() < (adapter2.getItemCount() - 1)){
                    layoutManager2.smoothScrollToPosition(recyclerView3, new RecyclerView.State(), layoutManager2.findLastCompletelyVisibleItemPosition() + 1);
                }else{
                    layoutManager2.smoothScrollToPosition(recyclerView3, new RecyclerView.State(), 0);
                }
            }
        }, 0, 4000);
        return view;
    }
}