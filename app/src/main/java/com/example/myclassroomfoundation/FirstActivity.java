package com.example.myclassroomfoundation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.Executor;

public class FirstActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    CoordinatorLayout layout;
    String contact_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        layout = findViewById(R.id.main_layout);
        bottomNavigationView = findViewById(R.id.nav_menu);
        bottomNavigationView.setBackground(null);

        contact_id = (String) userHelperClass.get(FirstActivity.this, "id", "id");

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL)){

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(FirstActivity.this, "Device doesn't have any fingerprint", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(FirstActivity.this, "Not working", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(FirstActivity.this, "No Fingerprint assigned", Toast.LENGTH_SHORT).show();
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new androidx.biometric.BiometricPrompt(FirstActivity.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(FirstActivity.this, "Authenticated", Toast.LENGTH_SHORT).show();
                layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("My Classroom Foundation")
                .setDescription("Confirm your fingerprint lock to access the app")
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL).build();

        biometricPrompt.authenticate(promptInfo);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                if(item.getItemId() == R.id.home){
                    fragment = new homeFragment();
                }
                else if(item.getItemId() == R.id.task){
                    fragment = new dashboardFragment();
                }else if(item.getItemId() == R.id.activity){
                    fragment = new classFragment();
                }else if(item.getItemId() == R.id.profile) {
                    if (userHelperClass.contains(FirstActivity.this, "id")) {
                        fragment = new profileFragment();
                    } else {
                        fragment = new loginFragment();
                    }
                }
                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                fragment)
                        .commit();
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(bottomNavigationView.getSelectedItemId() == R.id.home) {
            super.onBackPressed();
        }else{
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }
}